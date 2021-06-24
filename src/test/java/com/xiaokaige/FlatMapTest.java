package com.xiaokaige;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zengkai
 * @date 2021/6/23 15:08
 */
public class FlatMapTest {
    @Test
    public void test(){
        List<Integer> listOne = new ArrayList<>();
        List<Integer> listTwo = new ArrayList<>();

        listOne.add(1);listTwo.add(1);
        listOne.add(2);listTwo.add(2);

        List<int[]> result = listOne.stream().flatMap(one -> listTwo.stream().map(two -> new int[]{one, two})).collect(Collectors.toList());
    }
}
