package com.xiaokaige.service;

import com.xiaokaige.entity.UserTokenDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zk
 * @since 2021-06-24
 */
public interface UserTokenService extends IService<UserTokenDO> {

    void insertUserToken(String userId, String token);

}
