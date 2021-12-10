package qit.qjl.cookbook.service.delete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qit.qjl.cookbook.dao.DishDAO;
import qit.qjl.cookbook.entity.DishDO;
import qit.qjl.cookbook.exception.AuthorityException;
import qit.qjl.cookbook.exception.DishDeletedException;
import qit.qjl.cookbook.service.DishCollectService;
import qit.qjl.cookbook.service.DishCommentService;
import qit.qjl.cookbook.service.DishMaterialService;
import qit.qjl.cookbook.service.DishMethodService;

/**
 * @author: Cyan-K
 * @date: 2021/6/3
 */
@Service
public class DeleteDishServiceImpl implements DeleteDishService {

    @Autowired
    private DishDAO dishDAO;

    @Autowired
    private DishCommentService dishCommentService;

    @Autowired
    private DishMaterialService dishMaterialService;

    @Autowired
    private DishMethodService dishMethodService;

    @Autowired
    private DishCollectService dishCollectService;
    /**
     * 通过id,userId删除一道菜
     *
     * @param id dishId
     * @param userId userid
     * @return void
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void deleteDishById(Integer id, String userId) {
        DishDO dish = dishDAO.findByIdAndDishDel(id, 0);
        Integer dishId = dish.getId();
        if (dish == null) {
            throw new DishDeletedException();
        }
        if (userId.equals(dish.getUserId())) {
            // 删除菜基础信息
            dish.setDishDel(1);
            dishDAO.save(dish);

            // 删除菜的评论
            dishCommentService.deleteByDishId(dishId);

            // 删除菜的原料
            dishMaterialService.deleteAllByDishId(dishId);

            // 删除菜的制作步骤
            dishMethodService.deleteAllByDishId(dishId);

            // 删除被收藏的菜
            dishCollectService.deleteAllByDishId(dishId);

        }else {
            throw new AuthorityException();
        }
    }
}
