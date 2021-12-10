package qit.qjl.cookbook.pojo;

import qit.qjl.cookbook.entity.MaterialDO;
import qit.qjl.cookbook.entity.MethodDO;
import qit.qjl.cookbook.pojo.base.DishMethodBase;
import qit.qjl.cookbook.pojo.base.DishRawMaterialBase;

import java.util.ArrayList;
import java.util.List;

/**
 * @name: Cyan-K
 * @date: 2021/6/3
 */
public class MethodAndMaterialVO {

    private List<DishRawMaterialBase> dishRawMaterial;
    private List<DishMethodBase> dishMethod;

    public MethodAndMaterialVO() {
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

    public List<DishRawMaterialBase> getDishRawMaterial() {
        return dishRawMaterial;
    }

    public List<DishMethodBase> getDishMethod() {
        return dishMethod;
    }
}
