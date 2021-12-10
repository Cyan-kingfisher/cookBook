package qit.qjl.cookbook.service;

import qit.qjl.cookbook.entity.CommentDO;

import java.util.List;

/**
 * @author: Cyan-K
 * @date: 2021/5/20
 */
public interface DishCommentService {
    /**
     * 用户未一道菜添加评论
     *
     * @param userId 用户
     * @param dishId 菜
     * @param text 评论
     * @param img 图片
     * @return bool
     */
    boolean addComment(String userId, Integer dishId, String text, String img);

    /**
     * 查询某道菜的所有评论
     *
     * @param id dishId
     * @return list
     */
    List<CommentDO> queryCommentByDishId(Integer id);


    /**
     * 通过id删除一道菜
     *
     * @param id
     * @return
     */
    boolean deleteById(Integer id);


    /**
     * 删除一道菜的所有评论
     *
     * @param dishId
     * @return
     */
    boolean deleteByDishId(Integer dishId);
}
