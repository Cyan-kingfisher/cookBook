package qit.qjl.cookbook.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import qit.qjl.cookbook.common.Content;
import qit.qjl.cookbook.common.Result;
import qit.qjl.cookbook.entity.UserDO;
import qit.qjl.cookbook.service.UserInfoService;
import qit.qjl.cookbook.util.RedisUtil;

import java.io.IOException;


/**
 * @author: Cyan-K
 * @date: 2021/5/18
 */
@Slf4j
@CrossOrigin(origins = "*", maxAge = -1)
@RestController
public class PersonController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 上传用户资料
     * @param name 姓名
     * @param age 年龄
     * @param img 头像
     * @param cookies 菜品
     * @return result
     * @throws IOException
     */
    @PutMapping("/put/user")
    public Result info(String name, Integer age, MultipartFile img,  @CookieValue(Content.WEB_COOKIES) String cookies) throws IOException {
        String userId = (String)redisUtil.hget(Content.REDIS_COOKIE, cookies);
        return userInfoService.putUserInfo(name, age, img, userId);
    }

    /**
     *
     * @param cookies
     * @return
     * @throws IOException
     */
    @GetMapping("/user/info")
    public Result info(@CookieValue(Content.WEB_COOKIES) String cookies) throws IOException {
        String userId = (String)redisUtil.hget(Content.REDIS_COOKIE, cookies);
        UserDO userDO = userInfoService.queryUserInfo(userId);
        return Result.success("user", userDO);
    }

}
