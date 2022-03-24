package com.ipinyou.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ipinyou.user.entity.UserBaseEntity;
import common.domain.dto.UserBaseDto;
import common.domain.vo.UserBaseVo;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author wTai
 * @since 2022-01-26
 */
public interface IUserBaseService extends IService<UserBaseEntity> {

    boolean addUser(UserBaseDto userBaseDto);

    boolean updateUser(UserBaseDto userBaseDto);

    UserBaseVo getByUserId(Long userId);

    UserBaseVo getByUserIdCache(Long userId);
}
