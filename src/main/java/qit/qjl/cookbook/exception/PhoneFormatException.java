package qit.qjl.cookbook.exception;

/**
 * @author: Cyan-K
 * @date: 2021/5/18
 */
public class PhoneFormatException extends RuntimeException {
    public PhoneFormatException() {
        super("号码格式错误");
    }
}
