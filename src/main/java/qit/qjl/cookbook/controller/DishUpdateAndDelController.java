package qit.qjl.cookbook.controller;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import qit.qjl.cookbook.common.Content;
import qit.qjl.cookbook.common.Result;
import qit.qjl.cookbook.exception.ParamNullException;
import qit.qjl.cookbook.service.DishCollectService;
import qit.qjl.cookbook.service.delete.DeleteCommentService;
import qit.qjl.cookbook.service.delete.DeleteDishService;
import qit.qjl.cookbook.service.update.UpdateDishService;
import qit.qjl.cookbook.util.RedisUtil;

import java.io.IOException;

/**
 * 此类所有方法用于批量删除和菜的修改
 *
 * @name: Cyan-K
 * @date: 2021/6/3
 */
@RestController
@CrossOrigin(origins = "*", maxAge = -1)
public class DishUpdateAndDelController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private DeleteDishService deleteDishService;

    @Autowired
    private DishCollectService dishCollectService;

    @Autowired
    private DeleteCommentService deleteCommentService;

    @Autowired
    private UpdateDishService updateDishService;

    /**
     * 删除菜品信息
     * @param cookies userId
     * @param id dishId
     * @return result
     */
    @DeleteMapping("/delete/dish")
    public Result deleteDish(@CookieValue(Content.WEB_COOKIES) String cookies, Integer[] id) {
        String userId = (String)redisUtil.hget(Content.REDIS_COOKIE, cookies);
        if (id.length == 0) {
            throw new ParamNullException();
        }
        for (Integer integer : id) {
            deleteDishService.deleteDishById(integer, userId);
        }
        return Result.success();
    }

    @DeleteMapping("/delete/collect")
    public Result deteleCollect(@CookieValue(Content.WEB_COOKIES) String cookies, Integer[] id) {
        String userId = (String) redisUtil.hget(Content.REDIS_COOKIE, cookies);
        if (id.length == 0) {
            throw new ParamNullException();
        }
        for (Integer integer : id) {
            dishCollectService.deleteOneById(integer, userId);
        }
        return Result.success();
    }

    @DeleteMapping("/delete/comment")
    public Result deteleComment(@CookieValue(Content.WEB_COOKIES) String cookies, Integer[] id) {
        String userId = (String) redisUtil.hget(Content.REDIS_COOKIE, cookies);
        if (id.length == 0) {
            throw new ParamNullException();
        }
        for (Integer integer : id) {
            deleteCommentService.deleteCommentById(integer, userId);
        }
        return Result.success();
    }

    @PutMapping("/put/dish/id")
    public Result putDishOne(@CookieValue(Content.WEB_COOKIES) String cookies, @NotNull Integer id,
                             @NotNull String dishName, @NotNull String category, @NotNull String descript,
                             @NotNull MultipartFile dishImg, @NotNull String[] material,
                             @NotNull String[] count, @NotNull String[] stepText, @NotNull MultipartFile[] stepImg) throws IOException {
        String userId = (String) redisUtil.hget(Content.REDIS_COOKIE, cookies);
        updateDishService.updateDishBaseInfoById(userId, id, dishName, category, descript, dishImg, material, count, stepText, stepImg);
        return Result.success();
    }
}
