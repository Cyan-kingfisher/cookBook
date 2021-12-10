package qit.qjl.cookbook.exception;

/**
 * @author: Cyan-K
 * @date: 2021/5/20
 */
public class ShortSendException extends RuntimeException {
    public ShortSendException() {
        super("验证码发送失败");
    }
}
