package qit.qjl.cookbook.service.delete;

/**
 * @author: Cyan-K
 * @date: 2021/5/18
 */
public interface DeleteDishService {
    /**
     * 通过id,userId删除一道菜
     *
     * @param id id
     * @return void
     */
    void deleteDishById(Integer id, String userId);
}
