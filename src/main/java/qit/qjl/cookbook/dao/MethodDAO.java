package qit.qjl.cookbook.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import qit.qjl.cookbook.entity.MethodDO;

import java.util.List;

/**
 * @author: Cyan-K
 * @date: 2021/5/18
 */
@Repository
public interface MethodDAO extends JpaRepository<MethodDO, Integer> {
    List<MethodDO> findByDishIdAndMethodDel(Integer dishId, Integer methodDel);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update method set method_del=?1 where dish_id=?2")
    void updateAllByDishId(Integer del, Integer dishId);
}
