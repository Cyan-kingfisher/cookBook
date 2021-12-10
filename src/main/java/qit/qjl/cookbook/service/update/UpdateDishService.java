package qit.qjl.cookbook.service.update;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author: Cyan-K
 * @date: 2021/5/18
 */
public interface UpdateDishService {

    /**
     * 通过id修改菜
     *
     * @param id
     * @param dishName
     * @param category
     * @param descript
     * @param dishImg
     * @param material
     * @param count
     * @param stepText
     * @param stepImg
     * @return
     */
    void updateDishBaseInfoById(String userId, Integer id,  String dishName,  String category,
                                    String descript, MultipartFile dishImg,  String[] material,
                                    String[] count,  String[] stepText,  MultipartFile[] stepImg) throws IOException;
}
