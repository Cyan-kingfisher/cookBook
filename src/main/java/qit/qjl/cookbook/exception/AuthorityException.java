package qit.qjl.cookbook.exception;

/**
 * @name: Cyan-K
 * @date: 2021/6/8
 */
public class AuthorityException extends RuntimeException {
    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public AuthorityException() {
        super("您无权限操作其他客户的产品");
    }
}
