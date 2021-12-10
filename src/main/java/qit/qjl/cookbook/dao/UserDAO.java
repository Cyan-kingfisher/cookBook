package qit.qjl.cookbook.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qit.qjl.cookbook.entity.UserDO;

import java.util.List;
import java.util.Optional;

/**
 * @author: Cyan-K
 * @date: 2021/5/18
 */
@Repository
public interface UserDAO extends JpaRepository<UserDO, String> {

}
