package com.xiaokaige.entity;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: zk
 * Date: 2022/2/11
 * Time: 15:56
 */
public class TestEntity {
    public static void main(String[] args) {
        List<StudentDO> list = Stream.generate(StudentDO::new).limit(5).collect(Collectors.toList());
        System.out.println("list = " + list);
    }
}
