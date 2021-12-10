package qit.qjl.cookbook.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import qit.qjl.cookbook.common.Content;
import qit.qjl.cookbook.common.Result;
import qit.qjl.cookbook.service.DishCommentService;
import qit.qjl.cookbook.service.ImageUploadService;
import qit.qjl.cookbook.util.RedisUtil;

import java.io.IOException;

/**
 * @author: Cyan-K
 * @date: 2021/5/20
 */
@CrossOrigin(origins = "*", maxAge = -1)
@Slf4j
@RestController
public class CommentController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ImageUploadService imageUploadService;

    @Autowired
    private DishCommentService dishCommentService;

    /**
     * 添加对菜品的评论
     * @param cookies userId
     * @param dishId 菜品
     * @param comment 评论
     * @param img 图片
     * @return result
     * @throws IOException
     */
    @PostMapping("/comment/add")
    public Result info(@CookieValue(Content.WEB_COOKIES) String cookies, @NonNull Integer dishId,
                       @NonNull String comment, MultipartFile img) throws IOException {
        String userId = (String)redisUtil.hget(Content.REDIS_COOKIE, cookies);
        String commentImg = null;
        if (Content.fileIsBlack(img)) {
            commentImg = imageUploadService.storageImage(img).get(0);
        }
        boolean flag = dishCommentService.addComment(userId, dishId, comment, commentImg);
        if (flag) {
            return Result.success();
        }else {
            return Result.fail("403", "未知错误");
        }
    }

}
