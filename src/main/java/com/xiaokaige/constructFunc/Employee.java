package com.xiaokaige.constructFunc;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author: zk
 * Date: 2022/1/28
 * Time: 9:54
 */
@Data
@AllArgsConstructor
public class Employee implements Cloneable{
    private String name;

    private Integer age;

    private String address;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Employee employeeOne = new Employee("小曾", 18, "宝安");
        Employee employeeTwo = new Employee("小曾", 20, "宝安");
        Employee employeeThree = new Employee("小曾", 22, "宝安");
        List<Employee> list = Arrays.asList(employeeOne, employeeTwo, employeeThree);

    }

}
