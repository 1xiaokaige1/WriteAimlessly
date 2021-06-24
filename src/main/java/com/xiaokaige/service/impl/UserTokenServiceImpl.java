package com.xiaokaige.service.impl;

import com.xiaokaige.entity.UserTokenDO;
import com.xiaokaige.dao.UserTokenMapper;
import com.xiaokaige.service.UserTokenService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zk
 * @since 2021-06-24
 */
@Service
public class UserTokenServiceImpl extends ServiceImpl<UserTokenMapper, UserTokenDO> implements UserTokenService {

    @Autowired
    private UserTokenMapper userTokenMapper;

    @Override
    public void insertUserToken(String userId, String token) {
        UserTokenDO userTokenDO = new UserTokenDO();
        userTokenDO.setUserId(userId);
        userTokenDO.setToken(token);
        userTokenDO.setCreateTime(LocalDateTime.now());
        userTokenDO.setUpdateTime(LocalDateTime.now());
        userTokenMapper.insert(userTokenDO);
    }
}
