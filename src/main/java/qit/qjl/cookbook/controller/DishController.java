package qit.qjl.cookbook.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import qit.qjl.cookbook.common.Content;
import qit.qjl.cookbook.common.Result;
import qit.qjl.cookbook.entity.DishDO;
import qit.qjl.cookbook.exception.ParamCountErrorException;
import qit.qjl.cookbook.exception.ParamMissingException;
import qit.qjl.cookbook.exception.ParamNullException;
import qit.qjl.cookbook.pojo.DishHtmlVO;
import qit.qjl.cookbook.service.*;
import qit.qjl.cookbook.service.source.DishSourceService;
import qit.qjl.cookbook.util.RedisUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


/**
 * @author: Cyan-K
 * @date: 2021/5/18
 */
@Slf4j
@RestController
@CrossOrigin(origins = "*", maxAge = -1)
public class DishController {

    @Autowired
    private ImageUploadService imageUploadService;

    @Autowired
    private DishUploadService dishUploadService;

    @Autowired
    private DishShowService dishShowService;

    @Autowired
    private DishCollectService dishCollectService;

    @Autowired
    private DishDetaisService dishDetaisService;

    @Autowired
    private DishSourceService dishSourceService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 查看菜的类别
     * @return
     */
    @GetMapping("/cat/category")
    public Result getAllCategory() {
        List<String> list = dishShowService.queryAllCategory();
        return Result.success("category", list);
    }

    /**
     * 上传一道菜的基本信息
     *
     * @param name
     * @param img
     * @param category
     * @param descript
     * @param cookies
     * @return
     * @throws IOException
     */
    @PostMapping("/upload/dish/name")
    public Result dishName(String name, MultipartFile img, String category, String descript, @CookieValue(Content.WEB_COOKIES) String cookies) throws IOException {
        String userId = (String)redisUtil.hget(Content.REDIS_COOKIE, cookies);
        String image = imageUploadService.storageImage(img).get(0);
        boolean flag = dishUploadService.dishName(name, image, category, descript, userId);

        if (flag) {
            return Result.success("img", Content.IMG_URL + Content.IMG_STORAGE_ADDRESS + image);
        }else {
            return Result.fail("404", "待定");
        }
    }

    /**
     * 上传一道菜的详细信息
     *
     * @param material
     * @param count
     * @param stepText
     * @param cookies
     * @param stepImg
     * @return
     * @throws IOException
     * @throws ParamNullException
     * @throws ParamMissingException
     */
    @PostMapping("/upload/dish/method")
    public Result dishMethod(String[] material, String[] count, String[] stepText, @CookieValue(Content.WEB_COOKIES) String cookies, MultipartFile... stepImg) throws IOException, ParamNullException, ParamMissingException {
        String userId = (String)redisUtil.hget(Content.REDIS_COOKIE, cookies);

        if (material.length != count.length||stepText.length != stepImg.length) {
            throw new ParamCountErrorException();
        }

        if (stepImg.length == 0) {
            throw new ParamMissingException();
        }

        if (stepImg[0].isEmpty()) {
            throw new ParamNullException();
        }

        List<String> imgList = imageUploadService.storageImage(stepImg);

        boolean flag = dishUploadService.dishMethod(material, count, stepText, imgList, userId);
        HashMap<String, Object> map = new HashMap<>(1);
        if (flag) {
            String str = "img";
            for (int i = 1; i <= imgList.size(); i++) {
                map.put(str + i, Content.IMG_URL + Content
                        .IMG_STORAGE_ADDRESS + imgList.get(i - 1));
            }
            return Result.success(map);
        }else {
            return Result.fail("404", "待定");
        }
    }

    /**
     * 查看菜谱里面所有的菜
     *
     * @return result
     */
    @GetMapping("/show")
    public Result showProducts() {
        List<DishDO> list = dishShowService.queryAllDish();
        return Result.success("cookbooks", list);
    }

    /**
     * 收藏一道菜
     *
     * @param dishId
     * @return
     */
    @PostMapping("/collect/dish")
    public Result collectAdd(Integer dishId, @CookieValue(Content.WEB_COOKIES) String cookies) {
        String userId = (String)redisUtil.hget(Content.REDIS_COOKIE, cookies);
        boolean flag = dishCollectService.collectDish(userId, dishId);
        if (flag) {
            return Result.success();
        }else {
            return Result.fail("404", "收藏错误");
        }
    }

    /**
     * 查看所有收藏的菜
     *
     * @param cookies
     * @return
     */
    @PostMapping("/collect/all")
    public Result collectAll(@CookieValue(Content.WEB_COOKIES) String cookies) {
        String userId = (String)redisUtil.hget(Content.REDIS_COOKIE, cookies);
        List<DishDO> list = dishCollectService.getAllCollectDish(userId);
        return Result.success("dishList", list);
    }

    /**
     * 查看某道菜的详细信息
     *
     * @param cookies user
     * @param dishId dish_id
     * @return result
     */
    @PostMapping("/dish/detail")
    public Result catDishById(@CookieValue(Content.WEB_COOKIES) String cookies, Integer dishId) {
        DishHtmlVO dishHtmlVO = dishDetaisService.infoCollect(dishId);
        return Result.success("dishInfo", dishHtmlVO);
    }

    /**
     * 查询自己分享的菜
     *
     * @param cookies
     * @return
     */
    @PostMapping("/mydishes")
    public Result catMyDishes(@CookieValue(Content.WEB_COOKIES) String cookies) {
        String userId = (String)redisUtil.hget(Content.REDIS_COOKIE, cookies);

        List<DishDO> list = dishShowService.queryAllMyDish(userId);
        log.info(userId);
        return Result.success("cookbooks", list);
    }

    /**
     * 菜的搜索
     *
     * @param key
     * @return
     */
    @GetMapping("/source")
    public Result sourceDish(String key) {
        if (Content.strIsBlack(key)) {
            throw new ParamNullException();
        }
        List<DishDO> list = dishSourceService.sourceByKey(key);
        return Result.success("cookbooks", list);
    }

    @GetMapping("/show/dish/category")
    public Result getDish(@NonNull String category) {
        log.info("category: " + category);
        List<DishDO> list = dishShowService.queryByCategory(category);
        return Result.success("cookbooks", list);
    }

}
