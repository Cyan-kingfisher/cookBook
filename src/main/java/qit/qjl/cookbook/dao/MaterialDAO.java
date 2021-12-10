package qit.qjl.cookbook.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import qit.qjl.cookbook.entity.MaterialDO;

import java.util.List;

/**
 * @author: Cyan-K
 * @date: 2021/5/18
 */
@Repository
public interface MaterialDAO extends JpaRepository<MaterialDO, Integer> {

    @Query(
            nativeQuery = true,
            value = "select dish_id from materials where material_del=0 and raw_material like ?1"
    )
    List<Integer> sourceDishIdByKey(String key);

    List<MaterialDO> findByDishIdAndMaterialDel(Integer dishId, Integer materialDel);

    List<MaterialDO> findByDishId(Integer dishId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update materials set material_del=?1 where dish_id=?2")
    void updateAllByDishId(Integer del, Integer dishId);


}
