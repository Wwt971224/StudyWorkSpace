package common.domain.vo;

import lombok.Data;

@Data
public class UserBaseVo {

    /**
     * 主键用户id
     */
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
