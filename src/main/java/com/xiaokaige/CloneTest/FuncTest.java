package com.xiaokaige.CloneTest;

import com.xiaokaige.entity.StudentDO;

/**
 * @author: zk
 * Date: 2022/1/19
 * Time: 9:40
 */
public class FuncTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        CloneTest cloneTest = new CloneTest();
        StudentDO studentDO = new StudentDO(1L, "xx", 20, "mm");
        cloneTest.setName("zz");
        cloneTest.setAddress("ss");
        cloneTest.setStudentDO(studentDO);

        CloneTest cloneObj = cloneTest.clone();
        cloneObj.getStudentDO().setAge(26);
        System.out.println("cloneObj = " + cloneObj);
        System.out.println("cloneTest = " + cloneTest);

    }
}
