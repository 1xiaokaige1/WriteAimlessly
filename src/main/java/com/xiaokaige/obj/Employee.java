package com.xiaokaige.obj;

import lombok.Data;

/**
 * @author: zk
 * Date: 2022/1/17
 * Time: 14:20
 */
@Data
public class Employee {
    private String name;

    private Integer age;

    private String address;

    private Double salary;

    public static void methodOne(){}

    public static void methodOne(String paramOne){}
}
