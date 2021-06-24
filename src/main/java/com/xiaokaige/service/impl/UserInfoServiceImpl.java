package com.xiaokaige.service.impl;

import com.xiaokaige.entity.UserInfoDO;
import com.xiaokaige.dao.UserInfoMapper;
import com.xiaokaige.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zk
 * @since 2021-06-24
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfoDO> implements UserInfoService {

}
