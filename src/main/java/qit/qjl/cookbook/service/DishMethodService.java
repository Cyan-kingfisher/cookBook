package qit.qjl.cookbook.service;

import qit.qjl.cookbook.entity.MethodDO;

import java.util.List;

/**
 * @author: Cyan-K
 * @date: 2021/5/27
 */
public interface DishMethodService {
    /**
     * 用户通过id查看菜品制作方法
     * @param dishId
     * @return
     */
    List<MethodDO> queryByDishId(Integer dishId);

    /**
     *
     * @param dishId
     * @return
     */
    boolean deleteAllByDishId(Integer dishId);
}
