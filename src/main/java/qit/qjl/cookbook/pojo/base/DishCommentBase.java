package qit.qjl.cookbook.pojo.base;

import lombok.Data;
import qit.qjl.cookbook.entity.UserDO;

/**
 * @name: Cyan-K
 * @date: 2021/6/3
 */
@Data
public class DishCommentBase {
    private Integer id;
    private UserDO user;
    private String comment;
    private String img;
    private String time;
}
