package com.xiaokaige.service.impl;

import com.xiaokaige.dao.UserMapper;
import com.xiaokaige.queryParam.UserParam;
import com.xiaokaige.rongcloud.RongClient;
import com.xiaokaige.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zengkai
 * @date 2021/7/2 10:03
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RongClient rongClient;

    @Override
    public String getToken(String uid, String nickname, String portrait, String flag) {
        return "abc";
    }

    @Override
    public void updateUserInfo(String uid, UserParam userParam) {

    }
}
