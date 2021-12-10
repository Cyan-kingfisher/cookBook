package qit.qjl.cookbook.pojo;

import qit.qjl.cookbook.entity.*;
import qit.qjl.cookbook.pojo.base.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Cyan-K
 * @date: 2021/5/18
 */
public class DishHtmlVO implements Serializable {

    private UserDO userDO;
    private DishDO dishDO;
    private List<DishRawMaterialBase> dishRawMaterial;
    private List<DishMethodBase> dishMethod;
    private List<DishCommentBase> dishComment;

    public UserDO getUserDO() {
        return userDO;
    }

    public void setUserDO(UserDO userDO) {
        this.userDO = userDO;
    }

    public DishDO getDishDO() {
        return dishDO;
    }

    public void setDishDO(DishDO dishDO) {
        this.dishDO = dishDO;
    }

    public List<DishRawMaterialBase> getDishRawMaterial() {
        return dishRawMaterial;
    }

    public List<DishMethodBase> getDishMethod() {
        return dishMethod;
    }

    public List<DishCommentBase> getDishComment() {
        return dishComment;
    }

    public void setDishRawMaterial(List<MaterialDO> materials) {
        this.dishRawMaterial = new ArrayList<>(materials.size());
        for (MaterialDO var:
             materials) {
            DishRawMaterialBase dishRawMaterial = new DishRawMaterialBase();
            dishRawMaterial.setRaw(var.getRawMaterial());
            dishRawMaterial.setCount(var.getMaterialCount());
            this.dishRawMaterial.add(dishRawMaterial);
        }
    }


    public void setDishMethod(List<MethodDO> methods) {
        this.dishMethod = new ArrayList<>(methods.size());
        for (MethodDO var:
             methods) {
            DishMethodBase dishMethod = new DishMethodBase();
            dishMethod.setStepText(var.getMethodDescript());
            dishMethod.setImg(var.getMethodImg());
            this.dishMethod.add(dishMethod);
        }
    }

    public void setDishComment(List<CommentDO> comments, List<UserDO> users) {
        this.dishComment = new ArrayList<>(comments.size());
        for (int i = 0;i < comments.size();i++) {
            CommentDO var = comments.get(i);
            DishCommentBase dishComment = new DishCommentBase();
            dishComment.setId(var.getId());
            dishComment.setComment(var.getCommentText());
            dishComment.setImg(var.getCommentImg());
            dishComment.setTime(var.getUpdateTime().toString());
            dishComment.setUser(users.get(i));
            this.dishComment.add(dishComment);
        }
    }

}
