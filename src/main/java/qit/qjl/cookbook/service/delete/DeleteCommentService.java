package qit.qjl.cookbook.service.delete;

/**
 * @author: Cyan-K
 * @date: 2021/5/18
 */
public interface DeleteCommentService {
    /**
     * 通过id,userId删除一条自己的评论
     *
     * @param id
     * @return
     */
    void deleteCommentById(Integer id, String userId);
}
