package qit.qjl.cookbook.service.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import qit.qjl.cookbook.dao.DishDAO;
import qit.qjl.cookbook.dao.MaterialDAO;
import qit.qjl.cookbook.dao.MethodDAO;
import qit.qjl.cookbook.entity.DishDO;
import qit.qjl.cookbook.entity.MaterialDO;
import qit.qjl.cookbook.entity.MethodDO;
import qit.qjl.cookbook.exception.*;
import qit.qjl.cookbook.service.ImageUploadService;

import java.io.IOException;
import java.util.List;

/**
 * @name: Cyan-K
 * @date: 2021/6/22
 */
@Service
public class UpdateDishServiceImpl implements UpdateDishService {

    @Autowired
    private ImageUploadService imageUploadService;

    @Autowired
    private DishDAO dishDAO;

    @Autowired
    private MaterialDAO materialDAO;

    @Autowired
    private MethodDAO methodDAO;

    /**
     * 通过id修改菜
     *
     * @param id
     * @param dishName
     * @param category
     * @param descript
     * @param dishImg
     * @param material
     * @param count
     * @param stepText
     * @param stepImg
     * @return
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void updateDishBaseInfoById(String userId, Integer id, String dishName, String category, String descript, MultipartFile dishImg, String[] material, String[] count, String[] stepText, MultipartFile[] stepImg) throws IOException {

        DishDO dishDO = dishDAO.getOne(id);

        if (dishDO.getDishDel()==1) {
            throw new DishDeletedException();
        }
        if (!userId.equals(dishDO.getUserId())) {
            throw new AuthorityException();
        }
        if (material.length!=count.length||stepText.length!=stepImg.length) {
            throw new ParamCountErrorException();
        }
        if (stepImg.length==0) {
            throw new ParamMissingException();
        }
        if (stepImg[0].isEmpty()) {
            throw new ParamNullException();
        }

        // 基本信息修改
        List<String> list_0 =  imageUploadService.storageImage(dishImg);
        dishDO.setDishImg(list_0.get(0));
        dishDO.setDishDescript(descript);
        dishDO.setDishName(dishName);
        dishDO.setDishCategory(category);

        // 原料修改
        List<MaterialDO> listMaterialT = materialDAO.findByDishIdAndMaterialDel(id, 0);
        List<MaterialDO> listMaterialF = materialDAO.findByDishIdAndMaterialDel(id, 1);

        var index_raw = 0;

        // listT >= material.length
        var delC = listMaterialT.size() - material.length;
        for (int i = 0; i < listMaterialT.size(); i++) {
            var v = listMaterialT.get(i);
            if (i < delC) {
                v.setMaterialDel(1);
            }else {
                v.setMaterialCount(count[index_raw]);
                v.setRawMaterial(material[index_raw]);
                index_raw++;
            }

            materialDAO.save(v);
        }

        // listT < material.length
        var lengthF = listMaterialF.size();
        var addC = -delC;
        for (int i = 0; i < Math.min(addC, lengthF); i++) {
            var v = listMaterialF.get(i);
            v.setMaterialDel(0);
            v.setMaterialCount(count[index_raw]);
            v.setRawMaterial(material[index_raw]);
            index_raw++;
            materialDAO.save(v);
        }
        for (int i = 0; i < addC-lengthF; i++) {
            MaterialDO v = new MaterialDO();
            v.setDishId(id);
            v.setRawMaterial(material[index_raw]);
            v.setMaterialCount(count[index_raw]);
            index_raw++;
            materialDAO.save(v);
        }

        // 步骤修改
        List<MethodDO> listMethodT = methodDAO.findByDishIdAndMethodDel(id, 0);
        List<MethodDO> listMethodF = methodDAO.findByDishIdAndMethodDel(id, 1);
        List<String> stepImages = imageUploadService.storageImage(stepImg);
        var meIndex = 0;

        // listT >= method.length
        var delM = listMethodT.size() - stepText.length;
        for (int i = 0; i < listMethodT.size(); i++) {
            var v = listMethodT.get(i);
            if (i < delM) {
                v.setMethodDel(1);
            }else {
                v.setMethodDescript(stepText[meIndex]);
                v.setMethodImg(stepImages.get(meIndex));
                meIndex++;
            }
            methodDAO.save(v);
        }

        // listT < material.length
        var lengthMeF = listMaterialF.size();
        var addMeC = -delC;
        for (int i = 0; i < Math.min(addMeC, lengthMeF); i++) {
            var v = listMethodF.get(i);
            v.setMethodDel(0);
            v.setMethodDescript(stepText[meIndex]);
            v.setMethodImg(stepImages.get(meIndex));
            meIndex++;
            methodDAO.save(v);
        }
        for (int i = 0; i < addMeC- lengthMeF; i++) {
            var v = new MethodDO();
            v.setDishId(id);
            v.setMethodDescript(stepText[meIndex]);
            v.setMethodImg(stepImages.get(meIndex));
            meIndex++;
            methodDAO.save(v);
        }

    }
}
