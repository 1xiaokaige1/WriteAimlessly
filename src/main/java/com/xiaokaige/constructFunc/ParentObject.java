package com.xiaokaige.constructFunc;

/**
 * @author: zk
 * Date: 2022/1/27
 * Time: 14:59
 */
public class ParentObject {
    private String name;

    private Integer age;

    public ParentObject(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public ParentObject() {
        System.out.println("无参构造");
    }
}
