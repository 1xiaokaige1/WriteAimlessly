package com.xiaokaige.predicate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author zengkai
 * @date 2021/6/17 9:02
 */
public class TestFunction {
    public static String getFileInfo(TestPredicate p) throws IOException {
        try(BufferedReader br =
                    new BufferedReader(new FileReader("D:\\ideaProject\\test\\src\\main\\resources\\test"))){
            return p.processFile(br);
        }
    }

    public static <T> List<T> filterData(List<T> list, Predicate<T> p){
        List<T> resultList = new ArrayList<>();
        for (T t : list) {
            if(p.test(t)){
                resultList.add(t);
            }
        }
        return resultList;
    }
}
