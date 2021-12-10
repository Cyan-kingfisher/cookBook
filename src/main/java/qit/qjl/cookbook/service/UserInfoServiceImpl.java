package qit.qjl.cookbook.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import qit.qjl.cookbook.common.Content;
import qit.qjl.cookbook.common.Result;
import qit.qjl.cookbook.dao.UserDAO;
import qit.qjl.cookbook.entity.UserDO;
import qit.qjl.cookbook.exception.UserPutExcetion;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: Cyan-K
 * @date: 2021/5/18
 */
@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService{

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ImageUploadService imageUploadService;

    /**
     * 用户手机登录显示姓名，年龄，头像，手机号
     * @param name
     * @param age
     * @param img
     * @param phone
     * @return
     * @throws IOException
     */
    @Override
    public Result putUserInfo (String name, Integer age, MultipartFile img, String phone) throws IOException {
        if (!Content.strIsBlack(name)||age!=null|| Content.fileIsBlack(img)) {
            String image = null;
            if (Content.fileIsBlack(img)) {
                image = imageUploadService.storageImage(img).get(0);
                image = Content.IMG_URL+Content.IMG_STORAGE_ADDRESS+image;
            }
            UserDO userDO = userDAO.findById(phone).get();
            Map<String, Object> map = new LinkedHashMap<>();
            if (image!=null) {
                log.info("avatar:"+image);
                map.put("avatar", image);
                userDO.setUserImg(image);
            }
            if (!Content.strIsBlack(name)){
                log.info("name:"+name);
                map.put("name", name);
                userDO.setUserName(name);
            }
            if (age!=null){
                log.info("age:"+age);
                map.put("age", age);
                userDO.setUserAge(age);
            }
            userDAO.save(userDO);
            return Result.success(map);
        }else {
            throw new UserPutExcetion();
        }

    }

    @Override
    public UserDO queryUserInfo(String phone) {
        UserDO userDO = userDAO.findById(phone).get();
        return userDO;
    }

}
