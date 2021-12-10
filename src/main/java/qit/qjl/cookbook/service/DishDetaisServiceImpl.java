package qit.qjl.cookbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qit.qjl.cookbook.entity.*;
import qit.qjl.cookbook.pojo.DishHtmlVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Cyan-K
 * @date: 2021/5/20
 */
@Service
public class DishDetaisServiceImpl implements DishDetaisService {

    @Autowired
    private DishShowService dishShowService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private DishCommentService dishCommentService;

    @Autowired
    private DishMethodService dishMethodService;

    @Autowired
    private DishMaterialService dishMaterialService;

    /**
     * 通过id查询收集菜品的所有信息
     * @param dishId
     * @return
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public DishHtmlVO infoCollect (Integer dishId) {

        DishHtmlVO dishHtmlVO = new DishHtmlVO();

        DishDO dishDO = dishShowService.queryById(dishId);
        dishHtmlVO.setDishDO(dishDO);

        UserDO userDO = userInfoService.queryUserInfo(dishDO.getUserId());
        dishHtmlVO.setUserDO(userDO);

        List<MethodDO> methods = dishMethodService.queryByDishId(dishId);
        dishHtmlVO.setDishMethod(methods);

        List<MaterialDO> materials = dishMaterialService.queryByDishId(dishId);
        dishHtmlVO.setDishRawMaterial(materials);

        List<CommentDO> comments = dishCommentService.queryCommentByDishId(dishId);
        List<UserDO> users = new ArrayList<>(comments.size());

        for (CommentDO var:
             comments) {
            users.add(userInfoService.queryUserInfo(var.getUserId()));
        }

        dishHtmlVO.setDishComment(comments, users);

        return dishHtmlVO;
    }

}
