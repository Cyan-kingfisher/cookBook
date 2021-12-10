package qit.qjl.cookbook.service.delete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qit.qjl.cookbook.dao.CommentDAO;
import qit.qjl.cookbook.entity.CommentDO;
import qit.qjl.cookbook.exception.AuthorityException;

/**
 * @name: Cyan-K
 * @date: 2021/6/8
 */
@Service
public class DeleteCommentServiceImpl implements DeleteCommentService {

    @Autowired
    private CommentDAO commentDAO;

    /**
     * 通过id,userId删除一条自己的评论
     *
     * @param id id
     * @param userId userid
     * @return void
     */
    @Override
    public void deleteCommentById(Integer id, String userId) {
        CommentDO commentDO = commentDAO.getOne(id);
        if (userId.equals(commentDO.getUserId())) {
            commentDAO.updateById(1, id);
        }else {
            throw new AuthorityException();
        }
    }
}
