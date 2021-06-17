package com.xiaokaige.service.impl;

import com.xiaokaige.entity.StudentDO;
import com.xiaokaige.dao.StudentMapper;
import com.xiaokaige.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zk
 * @since 2021-06-15
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, StudentDO> implements StudentService {

}
