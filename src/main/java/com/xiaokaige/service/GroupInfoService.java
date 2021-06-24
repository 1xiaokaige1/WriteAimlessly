package com.xiaokaige.service;

import com.xiaokaige.entity.GroupInfoDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 群组信息表 服务类
 * </p>
 *
 * @author zk
 * @since 2021-06-24
 */
public interface GroupInfoService extends IService<GroupInfoDO> {

    void insertGroupInfo(String groupId, String groupName);

    void updateGroupInfo(String groupId, String groupName);
}
