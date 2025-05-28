package pub.shawfix.forum.infrastructure.dal.dataobject;

import lombok.Data;
import pub.shawfix.forum.common.enums.IsDeletedEn;

import java.io.Serializable;
import java.util.Date;

/**
 * @author shawfix
 * @create 2025/5/28 15:20
 * @desc
 **/
@Data
public class BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;


    /**
     * 是否删除
     */
    private Integer isDelete;

    /**
     * 创建时间
     */
    private Date createAt;

    /**
     * 更新时间
     */
    private Date updateAt;

    public void initBase() {
        this.id = null;
        this.isDelete = IsDeletedEn.NOT_DELETED.getValue();
        this.createAt = new Date();
        this.updateAt = this.createAt;
    }
}
