package qit.qjl.cookbook.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: Cyan-K
 * @date: 2021/5/18
 * @description: 菜的步骤
 */
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
@Entity(name = "method")
@Data
public class MethodDO implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer dishId;

    private Integer stepId;

    private String methodImg;

    private String methodDescript;

    @Column(insertable = false)
    private Integer methodDel;

    @Column(insertable = false, updatable = false)
    private LocalDateTime createTime;

    @Column(insertable = false, updatable = false)
    private LocalDateTime updateTime;

}
