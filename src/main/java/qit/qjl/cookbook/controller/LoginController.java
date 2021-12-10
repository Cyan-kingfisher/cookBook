package qit.qjl.cookbook.controller;

import com.aliyuncs.exceptions.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import qit.qjl.cookbook.common.Result;
import qit.qjl.cookbook.exception.LoginErrorException;
import qit.qjl.cookbook.service.LoginService;
import qit.qjl.cookbook.service.ShortMessageService;

import javax.servlet.http.HttpServletResponse;


/**
 * @author: Cyan-K
 * @date: 2021/5/18
 */
@Slf4j
@CrossOrigin(origins = "*", maxAge = -1)
@RestController
public class LoginController {

    @Autowired
    private ShortMessageService shortMessageService;

    @Autowired
    private LoginService loginService;

    /**
     * 向手机发送短信验证码
     * @param phone 手机号
     * @return result
     * @throws ClientException
     */
    @PostMapping("/phone-login/send")
    public Result loginSendCode(String phone) throws ClientException {
        shortMessageService.sendShortMessage(phone);
        return Result.success();
    }

    /**
     * 手机验证码登陆
     * @param phone 手机号
     * @param code 验证码
     * @param response 响应
     * @return result
     */
    @PostMapping("/phone-login/verification")
    public Result loginCodeVerification(String phone, String code, HttpServletResponse response) {
        boolean flag = shortMessageService.verification(phone, code);
        if (!flag) {
            throw new LoginErrorException();
        }
        loginService.UserInjection(phone, response);
        loginService.addUser(phone);
        return Result.success();
    }
}
