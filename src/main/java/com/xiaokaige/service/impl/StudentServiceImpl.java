package com.xiaokaige.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaokaige.entity.StudentDO;
import com.xiaokaige.dao.StudentMapper;
import com.xiaokaige.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaokaige.vo.StudentVO;
import org.apache.ibatis.builder.BuilderException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public StudentVO findSpecialStudent(String param) {
        StudentDO studentDO = studentMapper.selectOne(new QueryWrapper<StudentDO>().eq("yhzh", param));
        StudentVO studentVO = new StudentVO();
        BeanUtils.copyProperties(studentDO, studentVO);
        throw new BuilderException("fsdf");
        //return studentVO;
    }
}
