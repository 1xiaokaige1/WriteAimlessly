package com.xiaokaige.proxyFunc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zk
 * Date: 2022/1/28
 * Time: 14:34
 */
public class ProxyObj implements InvocationHandler {

    private InterfaceImpl anInterface;

    public ProxyObj(InterfaceImpl anInterface) {
        this.anInterface = anInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("111");
        Object obj = method.invoke(anInterface, args);
        System.out.println("222");
        return obj;
    }

    public static void main(String[] args) {
        List<?> list = new ArrayList<>();

        List<String> stringList = new ArrayList<>();
        stringList.add("A");
        stringList.add("B");
        stringList.add("C");

        test(stringList);
    }

    public static  void test(List<?> list){
        for (Object o : list) {

        }
    }
}
