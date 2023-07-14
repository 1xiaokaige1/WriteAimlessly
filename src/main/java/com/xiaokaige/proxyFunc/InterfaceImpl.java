package com.xiaokaige.proxyFunc;

/**
 * @author: zk
 * Date: 2022/1/28
 * Time: 13:42
 */
public class InterfaceImpl implements Product{
    @Override
    public void buy() {
        System.out.println("买了一点东西");
    }
}
