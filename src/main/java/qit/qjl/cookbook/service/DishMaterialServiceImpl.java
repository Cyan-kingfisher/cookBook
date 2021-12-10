package qit.qjl.cookbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qit.qjl.cookbook.dao.MaterialDAO;
import qit.qjl.cookbook.entity.MaterialDO;

import java.util.List;

/**
 * @author: Cyan-K
 * @date: 2021/5/27
 */
@Service
public class DishMaterialServiceImpl implements DishMaterialService {


    @Autowired
    private MaterialDAO materialDAO;

    /**
     * 用户通过id查看菜品材料
     * @param id
     * @return
     */
    @Override
    public List<MaterialDO> queryByDishId(Integer id) {
        return materialDAO.findByDishIdAndMaterialDel(id, 0);
    }

    @Override
    public void deleteAllByDishId(Integer dishId) {
        materialDAO.updateAllByDishId(1, dishId);
    }

}
