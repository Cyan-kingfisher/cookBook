package qit.qjl.cookbook.common;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author: Cyan-K
 * @date: 2021/5/18
 */
public final class Content {

    public static final String IMG_URL = "http://" + "localhost:8080" + "/cookbook/";
    public static final String IMG_SAVE_ADDRESS = "D:\\cookbook-image\\";

    public static final String IMG_STORAGE_ADDRESS = "image/";

    public static final String REDIS_DISH_NAME = "dishName";
    public static final String REDIS_LOGIN_VERIFICATION = "loginVerification";
    public static final String REDIS_COOKIE = "LOGIN_IN_COOKIE";
    public static final String WEB_COOKIES = "LOGIN_IN_COOKIE";

    public static boolean strIsBlack(String str) {
        return str == null||str.isBlank();
    }
    public static boolean fileIsBlack(MultipartFile file) {
        return file != null && !file.isEmpty();
    }

}
