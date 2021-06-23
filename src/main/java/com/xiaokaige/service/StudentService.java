package com.xiaokaige.service;

import com.xiaokaige.entity.StudentDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaokaige.vo.StudentVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zk
 * @since 2021-06-15
 */
public interface StudentService extends IService<StudentDO> {

    StudentVO findSpecialStudent(String param);

}
