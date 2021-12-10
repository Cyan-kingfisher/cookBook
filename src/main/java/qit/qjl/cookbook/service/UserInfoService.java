package qit.qjl.cookbook.service;

import org.springframework.web.multipart.MultipartFile;
import qit.qjl.cookbook.common.Result;
import qit.qjl.cookbook.entity.UserDO;

import java.io.IOException;
import java.util.List;

/**
 * @author: Cyan-K
 * @date: 2021/5/18
 */
public interface UserInfoService {
    /**
     * 用户手机登录显示姓名，年龄，头像，手机号
     * @param name
     * @param age
     * @param img
     * @param phone
     * @return
     * @throws IOException
     */
    public Result putUserInfo (String name, Integer age, MultipartFile img, String phone) throws IOException;
    public UserDO queryUserInfo (String phone);
}
