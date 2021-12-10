package qit.qjl.cookbook.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qit.qjl.cookbook.dao.CollectDAO;
import qit.qjl.cookbook.dao.DishDAO;
import qit.qjl.cookbook.entity.CollectDO;
import qit.qjl.cookbook.entity.DishDO;
import qit.qjl.cookbook.exception.AuthorityException;
import qit.qjl.cookbook.exception.DishDeletedException;
import qit.qjl.cookbook.exception.ParamNullException;
import qit.qjl.cookbook.exception.SomethingDeletedException;

import java.util.List;

/**
 * @author: Cyan-K
 * @date: 2021/5/18
 */
@Slf4j
@Service
public class DishCollectServiceImpl implements DishCollectService {

    @Autowired
    private CollectDAO collectDAO;

    @Autowired
    private DishDAO dishDAO;
    /**
     * 收藏一道菜
     *
     * @param phone 手机号
     * @param dishId 菜的id
     * @return bool
     */
    @Override
    public boolean collectDish(String phone, Integer dishId) {
        if (dishId == null) {
            throw new ParamNullException();
        }
        DishDO dishDO = dishDAO.getOne(dishId);
        if (dishDO.getDishDel() == 1) {
            throw new DishDeletedException();
        }
        CollectDO collectDO = new CollectDO();
        collectDO.setDishId(dishId);
        collectDO.setUserId(phone);
        CollectDO res = collectDAO.save(collectDO);
        return true;
    }

    @Override
    public List<DishDO> getAllCollectDish(String userId) {
        log.info("userid: " + userId);
        return dishDAO.findCollectAllByUserId(userId);
    }

    @Override
    public void deleteAllByDishId(Integer dishId) {
        collectDAO.updateAllByDishId(1, dishId);
    }

    @Override
    public void deleteAllByUserId(String userId) {
        collectDAO.updateAllByUserId(1, userId);
    }

    @Override
    public void deleteOneById(Integer id, String userId) {
        CollectDO collectDO = collectDAO.findCollectDOByDishIdAndUserIdAndCollectDel(id, userId, 0);
        if (collectDO == null) {
            throw new DishDeletedException();
        }
        collectDO.setCollectDel(1);
        collectDAO.save(collectDO);
    }

}
