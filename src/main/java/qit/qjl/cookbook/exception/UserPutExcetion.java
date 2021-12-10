package qit.qjl.cookbook.exception;

/**
 * @author: Cyan-K
 * @date: 2021/5/18
 */
public class UserPutExcetion extends RuntimeException {
    public UserPutExcetion() {
        super("不符合用户信息修改条件，请至少输入一个有效参数");
    }
}
