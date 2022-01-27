package com.ipinyou.user.comon;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 实体类基类
 *
 * @author hanzhaozhan
 * @date 2018-12-02 11:03
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode
public class BaseEntity {

    /**
     * 版本，乐观锁
     */
    @TableField(fill = FieldFill.INSERT)
    @Version
    private Integer version;

    /**
     * 是否删除 {@link com.ipinyou.user.enums.DeleteStatus}
     * 删除状态，1.删除 0.未删除
     */
    @TableLogic(delval = "1")
    private Boolean isDeleted;

    /**
     * 上次更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 最近一次修改人
     */
    private String updateBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    private String createBy;

}
