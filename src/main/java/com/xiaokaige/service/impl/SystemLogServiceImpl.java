package com.xiaokaige.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaokaige.dao.SystemLogMapper;
import com.xiaokaige.entity.SystemLogDO;
import com.xiaokaige.service.SystemLogService;
import org.springframework.stereotype.Service;

/**
 * @author zengkai
 * @date 2021/6/18 9:12
 */
@Service
public class SystemLogServiceImpl extends ServiceImpl<SystemLogMapper, SystemLogDO> implements SystemLogService {

}
