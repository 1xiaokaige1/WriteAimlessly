package com.xiaokaige.proxyFunc;

import java.lang.reflect.Proxy;

/**
 * @author: zk
 * Date: 2022/1/28
 * Time: 14:10
 */
public class FuncTest {
    public static void main(String[] args) {
        InterfaceImpl anInterface = new InterfaceImpl();
        Product product = (Product)Proxy.newProxyInstance(anInterface.getClass().getClassLoader(), anInterface.getClass().getInterfaces(), new ProxyObj(anInterface));
        product.buy();
    }
}
