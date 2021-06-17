package com.xiaokaige.mainstart;

import com.xiaokaige.entity.StudentDO;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author zengkai
 * @date 2021/6/17 16:33
 */
public class StartOne {
    public static void main(String[] args) {

        List<StudentDO> list = new ArrayList<>();

        StudentDO studentDOOne = new StudentDO(1L, "小曾", 24, "深圳市龙岗区");
        StudentDO studentDOTwo = new StudentDO(1L, "小陈", 16, "深圳市南山区");

        list.add(studentDOOne);
        list.add(studentDOTwo);

        List<String> resultList = methodOne(list, StudentDO::getAddress);
        System.out.println(resultList);
    }

    public static List<String> methodOne(List<StudentDO> list, Function<StudentDO, String> p) {
        List<String> resultList = new ArrayList<>();

        for (StudentDO studentDO : list) {
            String s = p.apply(studentDO);
            resultList.add(s);
        }
        return resultList;
    }

}
