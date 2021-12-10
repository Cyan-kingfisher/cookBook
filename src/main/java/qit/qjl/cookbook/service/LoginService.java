package qit.qjl.cookbook.service;

import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author: Cyan-K
 * @date: 2021/5/18
 */
public interface LoginService {
    /**
     * 用户手机登陆
     * @param phone
     * @param response
     * @return
     */
    boolean UserInjection (String phone, HttpServletResponse response);

    void addUser(String phone);
}
