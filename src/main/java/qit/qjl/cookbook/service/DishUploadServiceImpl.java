package qit.qjl.cookbook.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qit.qjl.cookbook.common.Content;

import qit.qjl.cookbook.dao.DishDAO;
import qit.qjl.cookbook.dao.MaterialDAO;
import qit.qjl.cookbook.dao.MethodDAO;
import qit.qjl.cookbook.entity.DishDO;
import qit.qjl.cookbook.entity.MaterialDO;
import qit.qjl.cookbook.entity.MethodDO;
import qit.qjl.cookbook.exception.DishnameNullException;
import qit.qjl.cookbook.util.RedisUtil;

import java.io.IOException;
import java.util.List;


/**
 * @author: Cyan-K
 * @date: 2021/5/18
 */
@Slf4j
@Service
public class DishUploadServiceImpl implements DishUploadService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private DishDAO dishDAO;

    @Autowired
    private MaterialDAO materialDAO;

    @Autowired
    private MethodDAO methodDAO;

    /**
     * 用户上传菜的名字，图片，类别，描述
     * @param name
     * @param img
     * @param category
     * @param descript
     * @param userId
     * @return
     * @throws IOException
     */
    @Override
    public boolean dishName(String name, String img, String category, String descript, String userId) throws IOException {

        DishDO dish = new DishDO();
        dish.setDishCategory(category);
        dish.setDishDescript(descript);
        dish.setDishImg(Content.IMG_URL + Content.IMG_STORAGE_ADDRESS+img);
        dish.setDishName(name);
        dish.setUserId(userId);

        redisUtil.set(Content.REDIS_DISH_NAME + userId, dish, 60*60);

        return true;
    }

    @Override
    @Transactional(rollbackFor = IOException.class)
    public boolean dishMethod(String[] material, String[] count, String[] stepText, List<String> imgList, String userId) throws IOException {

        if (!redisUtil.hasKey(Content.REDIS_DISH_NAME + userId)) {
            throw new DishnameNullException();
        }

        DishDO dish = (DishDO)redisUtil.get(Content.REDIS_DISH_NAME + userId);
        Integer dishId = dishDAO.save(dish).getId();
        log.info("菜名加入成果");

        for (int i = 1; i <= material.length; i++) {
            MaterialDO materialDO = new MaterialDO();
            materialDO.setDishId(dishId);
            materialDO.setRawMaterial(material[i - 1]);
            materialDO.setMaterialCount(count[i - 1]);
            materialDAO.save(materialDO);
        }
        log.info("菜原料加入成果");

        for (int i = 1; i <= stepText.length; i++) {
            MethodDO methodDO = new MethodDO();
            methodDO.setDishId(dishId);
            methodDO.setStepId(i);
            methodDO.setMethodImg(Content.IMG_URL + Content.IMG_STORAGE_ADDRESS + imgList.get(i - 1));
            methodDO.setMethodDescript(stepText[i - 1]);
            methodDAO.save(methodDO);
        }
        log.info("菜步骤加入成果");
        return true;
    }

}
