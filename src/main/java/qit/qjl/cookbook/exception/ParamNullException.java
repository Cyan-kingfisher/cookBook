package qit.qjl.cookbook.exception;

/**
 * @author: Cyan-K
 * @date: 2021/5/18
 */
public class ParamNullException  extends RuntimeException {
    public ParamNullException() {
        super("空指针错误，请检查上传参数的名字，或者不要上传空的文件或者空值");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
