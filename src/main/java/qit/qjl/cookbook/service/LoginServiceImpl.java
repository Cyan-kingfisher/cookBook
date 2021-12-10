package qit.qjl.cookbook.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qit.qjl.cookbook.common.Content;
import qit.qjl.cookbook.dao.UserDAO;
import qit.qjl.cookbook.entity.UserDO;
import qit.qjl.cookbook.util.RedisUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author: Cyan-K
 * @date: 2021/5/18
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService{

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserDAO userDAO;

    /**
     * 用户手机登陆
     * @param phone
     * @param response
     * @return
     */
    @Override
    public boolean UserInjection (String phone, HttpServletResponse response) {

        String uuid = UUID.randomUUID().toString();
        redisUtil.hset(Content.REDIS_COOKIE, uuid, phone, 60*60);

        Cookie cookie = new Cookie(Content.WEB_COOKIES, uuid);
        cookie.setMaxAge(60*60);
        cookie.setPath("/");

        response.addCookie(cookie);

        return true;

    }

    @Override
    public void addUser(String phone) {
        if (!userDAO.existsById(phone)) {
            UserDO userDO = new UserDO();
            userDO.setId(phone);
            userDAO.save(userDO);
        }
    }
}
