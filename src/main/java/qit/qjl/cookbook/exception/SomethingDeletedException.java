package qit.qjl.cookbook.exception;

/**
 * @name: Cyan-K
 * @date: 2021/6/8
 */
public class SomethingDeletedException extends RuntimeException {
    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public SomethingDeletedException() {
        super("请不要删除不存在的东西");
    }
}
