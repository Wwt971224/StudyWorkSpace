package com.ipinyou.jpastudy.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author wTai
 * @since 2022-01-26
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "user_base")
public class UserBaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键用户id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    /**
     * 用户真实姓名
     */
    private String realName;

    /**
     * 用户手机号
     */
    private Long userMobile;

    private Integer version;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserBaseEntity that = (UserBaseEntity) o;
        return userId != null && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
