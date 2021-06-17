package com.xiaokaige.streamtest;

import com.xiaokaige.entity.StudentDO;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author zengkai
 * @date 2021/6/16 16:25
 */
public class MultiMethod {

    public static void main(String[] args) {
        StudentDO studentDO = new StudentDO();
        studentDO.setId(1L);
        studentDO.setName("小曾");
        studentDO.setAge(25);
        studentDO.setAddress("深圳市龙岗区");
        StudentDO studentDO2 = new StudentDO();
        studentDO2.setId(2L);
        studentDO2.setName("小陈");
        studentDO2.setAge(25);
        studentDO2.setAddress("深圳市南山区");
        List<StudentDO> list = new ArrayList<>();
        list.add(studentDO);
        list.add(studentDO2);
        List<StudentDO> studentDOList = testPecMethod(list, MultiMethod::findStudent);
        System.out.println(studentDOList);

        List<StudentDO> resultList = list.stream()
                .filter(studentDOTwo -> studentDOTwo.getAddress().startsWith("深圳市"))
                .collect(Collectors.toList());
        System.out.println(resultList);
    }

    public static boolean findStudent(StudentDO studentDO) {
        if ("深圳市龙岗区".equals(studentDO.getAddress())) {
            return true;
        }
        return false;
    }


    public static List<StudentDO> testPecMethod(List<StudentDO> list, Predicate<StudentDO> func) {
        List<StudentDO> studentDOList = new ArrayList<>();
        list.forEach(studentDO -> {
            if (func.test(studentDO)) {
                studentDOList.add(studentDO);
            }
        });
        return studentDOList;
    }
}
