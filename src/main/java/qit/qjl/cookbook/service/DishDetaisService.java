package qit.qjl.cookbook.service;

import qit.qjl.cookbook.pojo.DishHtmlVO;

/**
 * @author: Cyan-K
 * @date: 2021/5/20
 */
public interface DishDetaisService {
    /**
     * 通过id查询收集菜品的所有信息
     * @param dishId
     * @return
     */
    DishHtmlVO infoCollect (Integer dishId);
}
