package qit.qjl.cookbook.exception;

/**
 * @author: Cyan-K
 * @date: 2021/5/18
 */
public class ParamCountErrorException extends RuntimeException {

    public ParamCountErrorException() {
        super("对应的参数数量不匹配");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

}
