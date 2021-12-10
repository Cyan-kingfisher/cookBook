package qit.qjl.cookbook.service;

import qit.qjl.cookbook.entity.MaterialDO;

import java.util.List;

/**
 * @author: Cyan-K
 * @date: 2021/5/27
 */
public interface DishMaterialService {
    /**
     * 用户通过id查看菜品材料
     * @param id
     * @return
     */
    List<MaterialDO> queryByDishId(Integer id);

    void deleteAllByDishId(Integer dishId);
}
