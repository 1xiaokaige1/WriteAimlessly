package com.xiaokaige.obj;

import java.lang.reflect.Field;

/**
 * @author: zk
 * Date: 2022/1/17
 * Time: 14:21
 */
public class ObjTest {
    public static void main(String[] args) {
        Manager manager = new Manager();
        manager.setName("abc");
        Class<? extends Manager> clazz = manager.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println("declaredField = " + declaredField);
        }
    }
}
