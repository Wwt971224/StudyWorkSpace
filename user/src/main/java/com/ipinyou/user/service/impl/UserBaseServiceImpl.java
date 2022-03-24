package com.ipinyou.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ipinyou.user.config.CacheConfig;
import com.ipinyou.user.entity.UserBaseEntity;
import com.ipinyou.user.repository.mysql.UserBaseDAO;
import com.ipinyou.user.service.IUserBaseService;
import common.domain.dto.UserBaseDto;
import common.domain.vo.UserBaseVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.slf4j.MDC;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author wTai
 * @since 2022-01-26
 */
@Slf4j
@Service
public class UserBaseServiceImpl extends ServiceImpl<UserBaseDAO, UserBaseEntity> implements IUserBaseService {

    @Override
    public boolean addUser(UserBaseDto userBaseDto) {
        UserBaseEntity userBaseEntity = new UserBaseEntity();
        BeanUtils.copyProperties(userBaseDto, userBaseEntity);
        return save(userBaseEntity);
    }

    @Override
    public boolean updateUser(UserBaseDto userBaseDto) {
        UserBaseEntity userBaseEntity = getById(userBaseDto.getUserId());
        BeanUtils.copyProperties(userBaseDto, userBaseEntity);
        return updateById(userBaseEntity);
    }

    @Override
    public UserBaseVo getByUserId(Long userId) {
        log.info(MDC.get("traceId"));
        UserBaseEntity userBaseEntity = getById(userId);

        if (Objects.isNull(userBaseEntity)) {
            return null;
        }
        UserBaseVo userBaseVo = new UserBaseVo();
        BeanUtils.copyProperties(userBaseEntity, userBaseVo);
        return userBaseVo;
    }

    @Cacheable(value = CacheConfig.USER_CACHE, key = "#userId", unless = "#result == null")
    @Override
    public UserBaseVo getByUserIdCache(Long userId) {
        UserBaseVo userBaseVo = new UserBaseVo();
        userBaseVo.setUserId(111L);
        userBaseVo.setRealName("111");
        userBaseVo.setUserMobile(111L);

        return userBaseVo;
    }
}
