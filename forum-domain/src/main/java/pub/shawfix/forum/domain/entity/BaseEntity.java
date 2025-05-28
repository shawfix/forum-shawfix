package pub.shawfix.forum.domain.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author shawfix
 * @create 2025/5/28 10:57
 * @desc
 **/
@Data
public abstract class BaseEntity {

    private Long id;

    /**
     * 创建时间
     */
    private Date createAt;

    /**
     * 更新时间
     */
    private Date updateAt;

}
