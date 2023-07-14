package com.xiaokaige.thread;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaokaige.entity.StudentDO;

/**
 * @author: zk
 * Date: 2022/2/10
 * Time: 14:32
 */
public class MultiThreadTest {

    public synchronized static void test01() {
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test02() {
        System.out.println("测试静态方法2...");
    }

    public static void main(String[] args) {
        test01();
        test02();
        StudentDO studentDO = new StudentDO();
        studentDO.setId(123L);
        studentDO.setAge(18);
        studentDO.setName("测试");
        studentDO.setAddress("无地址");

        ObjectMapper objectMapper = new ObjectMapper();
        String serializeStr = null;
        try {
            serializeStr = objectMapper.writeValueAsString(studentDO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("serializeStr = " + serializeStr);
    }
}
