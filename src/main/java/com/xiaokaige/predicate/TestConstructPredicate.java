package com.xiaokaige.predicate;

import java.util.function.Predicate;

/**
 * @author zengkai
 * @date 2021/6/17 10:06
 */
public interface TestConstructPredicate<T,V,U,Y,R> {
    /**
     * 测试自定义构造方法
     * @param t
     * @param v
     * @param u
     * @param y
     * @return
     */
    R apply(T t, V v, U u, Y y);
}
