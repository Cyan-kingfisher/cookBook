package qit.qjl.cookbook.exception;

/**
 * @name: Cyan-K
 * @date: 2021/6/3
 */
public class DishDeletedException extends RuntimeException {
    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public DishDeletedException() {
        super("这道菜不存在");
    }
}
