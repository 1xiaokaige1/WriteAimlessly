package com.xiaokaige.dao;

import com.xiaokaige.entity.StudentDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaokaige.vo.StudentVO;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zk
 * @since 2021-06-15
 */
@Repository
public interface StudentMapper extends BaseMapper<StudentDO> {

    /**
     * 根据用户id查找用户
     * @param id
     * @return
     */
    Integer findStudentById(Long id);

    /**
     * 根据用户id查找用户
     * @param userId
     * @return
     */
    StudentVO findStudentByIdTwo(Long userId);

}
