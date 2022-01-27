package common.domain.dto;

import lombok.Data;

@Data
public class UserBaseDto {

    /**
     * userId
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
