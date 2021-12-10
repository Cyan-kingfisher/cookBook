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
 * @description: 菜的收藏
 */
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
@Entity(name = "collect")
@Data
public class CollectDO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userId;

    private Integer dishId;

    @Column(insertable = false)
    private Integer collectDel;

    @Column(insertable = false, updatable = false)
    private LocalDateTime createTime;

    @Column(insertable = false, updatable = false)
    private LocalDateTime updateTime;

}
