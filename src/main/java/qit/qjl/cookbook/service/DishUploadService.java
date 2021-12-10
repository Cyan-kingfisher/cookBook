package qit.qjl.cookbook.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


/**
 * @author: Cyan-K
 * @date: 2021/5/18
 */
public interface DishUploadService {
    /**
     * 用户上传菜的名字，图片，类别，描述
     * @param name
     * @param img
     * @param category
     * @param descript
     * @param userId
     * @return
     * @throws IOException
     */
    boolean dishName(String name, String img, String category, String descript, String userId) throws IOException;
    boolean dishMethod(String[] material, String[] count, String[] stepText, List<String> imgList, String userId) throws IOException;
}
