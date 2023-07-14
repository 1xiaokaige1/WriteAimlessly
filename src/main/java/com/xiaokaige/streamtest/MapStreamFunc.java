package com.xiaokaige.streamtest;

import com.xiaokaige.entity.StudentDO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: zk
 * Date: 2022/2/16
 * Time: 11:14
 */
public class MapStreamFunc {
    public static void main(String[] args) throws IOException {
        StudentDO studentDOOne = new StudentDO(1L, "姓名1", 18, "地址1");
        StudentDO studentDOTwo = new StudentDO(2L, "姓名2", 18, "地址2");
        StudentDO studentDOThree = new StudentDO(1L, "姓名3", 18, "地址3");
        List<StudentDO> list = new ArrayList<>();
        list.add(studentDOOne);
        list.add(studentDOTwo);
        list.add(studentDOThree);
        Map<Long, StudentDO> studentDOMap = list
                .stream()
                .collect(Collectors.toMap(StudentDO::getId, Function.identity(), (o,n)->n));
        System.out.println("studentDOMap = " + studentDOMap);

        DataOutputStream out = new DataOutputStream(new FileOutputStream(new File("D:\\ideaProject\\test\\abc.txt")));
        out.write(2);
        out.close();
    }
}
