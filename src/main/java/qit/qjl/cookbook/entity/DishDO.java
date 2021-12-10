package qit.qjl.cookbook.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: Cyan-K
 * @date: 2021/5/18
 * @description: 菜
 */
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
@Entity(name = "dish")
@Data
public class DishDO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userId;

    private String dishName;

    private String dishImg;

    private String dishCategory;

    private String dishDescript;

    @Column(insertable = false)
    private Integer dishDel;

    @Column(insertable = false, updatable = false)
    private LocalDateTime createTime;

    @Column(insertable = false, updatable = false)
    private LocalDateTime updateTime;

    public DishDO(){}

}
