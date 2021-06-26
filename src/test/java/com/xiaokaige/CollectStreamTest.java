package com.xiaokaige;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zengkai
 * @date 2021/6/24 15:46
 */
public class CollectStreamTest {

    @Test
    public void test01(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        long count = list.stream().count();
        System.out.println(count);
    }
}
