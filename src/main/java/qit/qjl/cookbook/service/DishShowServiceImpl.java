package qit.qjl.cookbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qit.qjl.cookbook.dao.DishDAO;
import qit.qjl.cookbook.entity.DishDO;
import qit.qjl.cookbook.exception.ParamNullException;

import java.util.List;

/**
 * @author: Cyan-K
 * @date: 2021/5/18
 */
@Service
public class DishShowServiceImpl implements DishShowService {

    @Autowired
    private DishDAO dishDAO;

    /**
     * 用户通过自己手机查看自己上传的菜
     * @return
     */
    @Override
    public List<DishDO> queryAllDish() {
        return dishDAO.findAllByDishDel(0);
    }

    @Override
    public List<String> queryAllCategory() {
        return dishDAO.findCategoryALL();
    }

    @Override
    public DishDO queryById(Integer id) {
        if (id != null) {
            return dishDAO.findByIdAndDishDel(id, 0);
        }else {
            throw new ParamNullException();
        }
    }

    @Override
    public List<DishDO> queryAllMyDish(String phone) {
        return dishDAO.findAllByUserIdAndDishDel(phone, 0);
    }

    @Override
    public List<DishDO> queryByCategory(String category) {
        return dishDAO.findAllByDishCategoryAndAndDishDel(category, 0);
    }
}
