package com.xiaokaige.compare;

import com.xiaokaige.entity.StudentDO;

import java.util.Comparator;

/**
 * @author zengkai
 * @date 2021/6/17 17:49
 */
public class StudentComparator implements Comparator<StudentDO> {
    @Override
    public int compare(StudentDO o1, StudentDO o2) {
        return o1.getAge() - o2.getAge();
    }
}
