package qit.qjl.cookbook.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import qit.qjl.cookbook.entity.CollectDO;

import java.util.List;

/**
 * @author: Cyan-K
 * @date: 2021/5/18
 */
@Repository
public interface CollectDAO extends JpaRepository<CollectDO, Integer> {

    /**
     * 根据手机号查询菜的收藏信息
     *
     * @param userId 手机号
     * @return List
     */
    List<CollectDO> findAllByUserId(String userId);

    CollectDO findCollectDOByDishIdAndUserIdAndCollectDel(Integer dishId, String userId, Integer collectDel);
    /**
     *
     * @param del
     * @param dishId
     */
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update collect set collect_del=?1 where dish_id=?2")
    void updateAllByDishId(Integer del, Integer dishId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update collect set collect_del=?1 where user_id=?2")
    void updateAllByUserId(Integer del, String userId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update collect set collect_del=?1 where id=?2")
    void updateOneById(Integer del, Integer Id);

}
