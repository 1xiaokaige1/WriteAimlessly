package com.xiaokaige.streamtest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: zk
 * Date: 2022/2/16
 * Time: 10:13
 */
public class FuncTest {
    //flatmap将多个流合并为一个流
    public static void main(String[] args) {
        String[] list = new String[]{"abc", "bcd", "cde"};
        List<String> resultList = Stream.of(list).flatMap(FuncTest::getStream).collect(Collectors.toList());
        Optional<String> emptyOpt = Optional.empty();
        Optional<String> resultOpt = emptyOpt.flatMap(s -> Optional.of("2"));
        resultOpt.ifPresent(s-> System.out.println("s = " + s));
        System.out.println("list = " + Arrays.toString(resultList.toArray(new String[]{})));
    }

    public static Stream<String> getStream(String s){
        List<String> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++)
            list.add(s.substring(i, i + 1));
        return list.stream();
    }

}
