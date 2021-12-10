package qit.qjl.cookbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qit.qjl.cookbook.dao.MethodDAO;
import qit.qjl.cookbook.entity.MethodDO;

import java.util.List;

/**
 * @author: Cyan-K
 * @date: 2021/5/27
 */
@Service
public class DishMethodServiceImpl implements DishMethodService {

    /**
     * 用户通过id查看菜品制作方法
     * @param dishId
     * @return
     */
    @Autowired
    private MethodDAO methodDAO;

    @Override
    public List<MethodDO> queryByDishId(Integer dishId) {
        return methodDAO.findByDishIdAndMethodDel(dishId, 0);
    }

    /**
     * @param dishId
     * @return
     */
    @Override
    public boolean deleteAllByDishId(Integer dishId) {
        methodDAO.updateAllByDishId(1, dishId);
        return true;
    }

}
