package qit.qjl.cookbook.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import qit.qjl.cookbook.entity.DishDO;

import java.util.List;

/**
 * @author: Cyan-K
 * @date: 2021/5/18
 */
@Repository
public interface DishDAO extends JpaRepository<DishDO, Integer> {

    /**
     * 所有未被删除的菜
     *
     * @param dishDel 记录是否删除
     * @return List<DishDO>
     */
    List<DishDO> findAllByDishDel(Integer dishDel);

    /**
     * 查询该用户上传的菜
     *
     * @param userId 手机号
     * @return List<DishDO>
     */
    List<DishDO> findAllByUserId(String userId);

    /**
     * 查询用户收藏的菜，nativeQuery = true
     *
     * @param userId 手机号
     * @return
     */
    @Query(nativeQuery = true, value = "select * from dish where dish_del=0 and id in (select dish_id from collect where user_id=?1 and collect_del=0)")
    List<DishDO> findCollectAllByUserId(String userId);


    /**
     * 查询所有菜的类别， nativeQuery = true
     * @return
     */
    @Query(nativeQuery = true, value = "select distinct dish_category from dish where dish_del=0")
    List<String> findCategoryALL();

    /**
     * 具体查询某道菜
     *
     * @param dishId
     * @param dishDel
     * @return
     */
    DishDO findByIdAndDishDel(Integer dishId, Integer dishDel);

    /**
     * 查询我的菜
     *
     * @param userId
     * @param dishDel
     * @return
     */
    List<DishDO> findAllByUserIdAndDishDel(String userId, Integer dishDel);


    /**
     *
     * @param key 实际为 %key%
     * @return
     */
    @Query(
            nativeQuery = true,
            value = "select * from dish where dish_del=0 and dish_name like ?1"
    )
    List<DishDO> sourceAllByName(String key);

    List<DishDO> findAllByDishCategoryAndAndDishDel(String dishCategory, Integer dishDel);
}