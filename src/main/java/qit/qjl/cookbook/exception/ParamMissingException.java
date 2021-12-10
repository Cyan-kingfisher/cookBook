package qit.qjl.cookbook.exception;

/**
 * @author: Cyan-K
 * @date: 2021/5/18
 */
public class ParamMissingException extends RuntimeException  {

    public ParamMissingException() {
        super("必要参数缺失");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
