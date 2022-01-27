package com.ipinyou.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.ipinyou.user.comon.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author wTai
 * @since 2022-01-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("user_base")
public class UserBaseEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键用户id
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 用户真实姓名
     */
    private String realName;

    /**
     * 用户手机号
     */
    private Long userMobile;


}
