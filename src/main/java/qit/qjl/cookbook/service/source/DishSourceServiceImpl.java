package qit.qjl.cookbook.service.source;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qit.qjl.cookbook.dao.DishDAO;
import qit.qjl.cookbook.dao.MaterialDAO;
import qit.qjl.cookbook.entity.DishDO;

import java.util.ArrayList;
import java.util.List;

/**
 * @name: Cyan-K
 * @date: 2021/6/10
 */
@Slf4j
@Service
public class DishSourceServiceImpl implements DishSourceService {

    @Autowired
    private DishDAO dishDAO;

    @Autowired
    private MaterialDAO materialDAO;

    @Override
    public List<DishDO> sourceByKey(String key) {
        key = "%"+key+"%";
        List<DishDO> list = dishDAO.sourceAllByName(key);
        List<DishDO> res = new ArrayList<>(list);
        List<Integer> ids = materialDAO.sourceDishIdByKey(key);
        log.info(String.valueOf(ids.size()));
        for (Integer dishId:ids) {
            DishDO dishDO = dishDAO.getOne(dishId);
            if (dishDO.getDishDel()==0&&!list.contains(dishDO)) {
                res.add(dishDO);
            }
        }
        return res;
    }
}
