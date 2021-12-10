package qit.qjl.cookbook.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import qit.qjl.cookbook.entity.CommentDO;

import java.util.List;

/**
 * @author: Cyan-K
 * @date: 2021/5/18
 */
@Repository
public interface CommentDAO extends JpaRepository<CommentDO, Integer> {
    /**
     * 查询某菜的所有评论
     *
     * @param dishId
     * @param commentDel
     * @return
     */
    List<CommentDO> findAllByDishIdAndCommentDel(Integer dishId, Integer commentDel);

    /**
     *
     * @param del 逻辑删除 1表示已删除 0表示未删除
     * @param dishId
     */
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update comment set comment_del=?1 where dish_id=?2")
    void updateAllByDishId(Integer del, Integer dishId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update comment set comment_del=?1 where id=?2")
    void updateById(Integer del, Integer id);
}
