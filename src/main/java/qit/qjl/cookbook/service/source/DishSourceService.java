package qit.qjl.cookbook.service.source;

import qit.qjl.cookbook.entity.DishDO;

import java.util.List;

/**
 * @name: Cyan-K
 * @date: 2021/6/10
 */
public interface DishSourceService {
    /**
     * 通过关键字搜索
     *
     * @param key 在菜中搜索key
     * @return list
     */
    List<DishDO> sourceByKey(String key);
}
