package qit.qjl.cookbook.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: Cyan-K
 * @date: 2021/5/18
 * @description: 用户
 */
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
@Entity(name = "user")
@Data
public class UserDO implements Serializable {

    @Id
    private String id;

    private String userName;

    private Integer userAge;

    private String userImg;

    @Column(insertable = false, updatable = false)
    private LocalDateTime createTime;

    @Column(insertable = false, updatable = false)
    private LocalDateTime updateTime;

}
