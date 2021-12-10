package qit.qjl.cookbook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author: Cyan-K
 * @date: 2021/5/18
 */
public class LoginErrorException extends RuntimeException {
    public LoginErrorException() {
        super("请检查是否发送验证码，或者验证码格式错误，或者验证码与号码不匹配，或者号码填错了");
    }
}
