package qit.qjl.cookbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qit.qjl.cookbook.common.Content;
import qit.qjl.cookbook.dao.CommentDAO;
import qit.qjl.cookbook.dao.DishDAO;
import qit.qjl.cookbook.entity.CommentDO;
import qit.qjl.cookbook.entity.DishDO;
import qit.qjl.cookbook.exception.DishDeletedException;

import java.util.List;

/**
 * @author: Cyan-K
 * @date: 2021/5/20
 */
@Service
public class DishCommentServiceImpl implements DishCommentService {

    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private DishDAO dishDAO;

    /**
     * 用户未一道菜添加评论
     *
     * @param userId 用户
     * @param dishId 菜
     * @param text 评论
     * @param img 图片
     * @return bool
     */
    @Override
    public boolean addComment(String userId, Integer dishId, String text, String img) {
        DishDO dishDO = dishDAO.getOne(dishId);
        if (dishDO.getDishDel() == 1) {
            throw new DishDeletedException();
        }
        CommentDO commentDO = new CommentDO();
        commentDO.setDishId(dishId);
        commentDO.setUserId(userId);
        commentDO.setCommentText(text);
        if (img != null&&!img.equals("")) {
            commentDO.setCommentImg(Content.IMG_URL + Content.IMG_STORAGE_ADDRESS + img);
        }
        commentDAO.save(commentDO);
        return true;
    }

    @Override
    public List<CommentDO> queryCommentByDishId(Integer id) {
        return commentDAO.findAllByDishIdAndCommentDel(id, 0);
    }

    /**
     * 通过id删除一道菜
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(Integer id) {
        commentDAO.updateById(1, id);
        return false;
    }

    /**
     * 删除一道菜的所有评论
     *
     * @param dishId
     * @return
     */
    @Override
    public boolean deleteByDishId(Integer dishId) {
        commentDAO.updateAllByDishId(1, dishId);
        return true;
    }

}
