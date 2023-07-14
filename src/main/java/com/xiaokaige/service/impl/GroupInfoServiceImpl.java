package com.xiaokaige.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaokaige.dao.GroupInfoMapper;
import com.xiaokaige.entity.GroupInfoDO;
import com.xiaokaige.exception.BusinessException;
import com.xiaokaige.exception.GroupExceptionEnum;
import com.xiaokaige.response.ResponseInfo;
import com.xiaokaige.service.GroupInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 群组信息表 服务实现类
 * </p>
 *
 * @author zk
 * @since 2021-06-24
 */
@Service
public class GroupInfoServiceImpl extends ServiceImpl<GroupInfoMapper, GroupInfoDO> implements GroupInfoService {

    @Autowired
    private GroupInfoMapper groupInfoMapper;

    @Override
    public void insertGroupInfo(String groupId, String groupName) {
        GroupInfoDO groupInfoDO = new GroupInfoDO();
        groupInfoDO.setGroupId(groupId);
        groupInfoDO.setGroupName(groupName);
        groupInfoDO.setCreateTime(LocalDateTime.now());
        groupInfoDO.setUpdateTime(LocalDateTime.now());
        groupInfoMapper.insert(groupInfoDO);
    }

    @Override
    public void updateGroupInfo(String groupId, String groupName) {
        GroupInfoDO groupInfoDO = new GroupInfoDO();
        groupInfoDO.setGroupId(groupId);
        groupInfoDO.setGroupName(groupName);
        groupInfoDO.setUpdateTime(LocalDateTime.now());
        groupInfoMapper.updateById(groupInfoDO);
    }

    @Override
    public ResponseInfo<?> testException() {
        throw new BusinessException(GroupExceptionEnum.GROUP_NOT_EXIST);
    }
}
