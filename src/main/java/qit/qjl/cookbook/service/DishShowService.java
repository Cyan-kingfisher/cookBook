package qit.qjl.cookbook.service;

import qit.qjl.cookbook.entity.DishDO;

import java.util.List;

/**
 * @author: Cyan-K
 * @date: 2021/5/18
 */
public interface DishShowService {
    List<DishDO> queryAllDish();
    List<String> queryAllCategory();
    DishDO queryById(Integer id);
    List<DishDO> queryAllMyDish(String phone);
    List<DishDO> queryByCategory(String category);
}
