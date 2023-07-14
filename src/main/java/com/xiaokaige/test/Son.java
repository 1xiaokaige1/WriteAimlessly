package com.xiaokaige.test;

import com.xiaokaige.entity.GroupInfoDO;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Iterator;

/**
 * @author: zk
 * Date: 2023/2/17
 * Time: 11:07
 */
public class Son extends Parent{

    public static void main(String[] args) throws ClassNotFoundException, ParseException {
        /*Class<?> groupClassInfo = GroupInfoDO.class.getClassLoader().loadClass("com.xiaokaige.entity.GroupInfoDO");
        Method[] declaredMethods = groupClassInfo.getDeclaredMethods();
        Iterator<Method> iterator = Arrays.stream(declaredMethods).iterator();
        while(iterator.hasNext()){
            Method method = iterator.next();
            System.out.println("method = " + method);
        }*/
        long time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                .parse("2023-04-13 00:00:00").getTime();
        System.out.println("time = " + time);
    }
}
