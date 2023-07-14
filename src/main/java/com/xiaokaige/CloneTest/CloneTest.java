package com.xiaokaige.CloneTest;

import com.xiaokaige.entity.StudentDO;
import lombok.Data;

/**
 * @author: zk
 * Date: 2022/1/19
 * Time: 9:39
 */
@Data
public class CloneTest implements Cloneable {

    private String name;

    private String address;

    private StudentDO studentDO;

    @Override
    public CloneTest clone() throws CloneNotSupportedException {
        CloneTest clone = (CloneTest)super.clone();
        clone.studentDO = (StudentDO) studentDO.clone();
        return clone;
    }
}
