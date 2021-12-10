package qit.qjl.cookbook.service;

import qit.qjl.cookbook.entity.DishDO;

import java.util.List;

/**
 * @author: Cyan-K
 * @date: 2021/5/18
 */
public interface DishCollectService {
    /**
     * 收藏一道菜
     *
     * @param phone 手机号
     * @param dishId 菜的id
     * @return bool
     */
    boolean collectDish(String phone, Integer dishId);

    /**
     * 查看收藏的菜
     *
     * @param userId 用户id
     * @return List<DishDO>
     */
    List<DishDO> getAllCollectDish(String userId);

    void deleteAllByDishId(Integer dishId);

    void deleteAllByUserId(String userId);

    void deleteOneById(Integer id, String userId);
}
