package qit.qjl.cookbook.exception;

/**
 * @author: Cyan-K
 * @date: 2021/5/18
 */
public class DishnameNullException extends RuntimeException {

    public DishnameNullException() {
        super("请先输入菜名");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
