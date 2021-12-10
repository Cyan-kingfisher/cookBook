package qit.qjl.cookbook.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: Cyan-K
 * @date: 2021/5/18
 * @description: 评论
 */
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
@Entity(name = "comment")
@Data
public class CommentDO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer dishId;

    private String userId;

    private String commentText;

    private String commentImg;

    @Column(insertable = false)
    private Integer commentDel;

    @Column(insertable = false, updatable = false)
    private LocalDateTime createTime;

    @Column(insertable = false, updatable = false)
    private LocalDateTime updateTime;

}
