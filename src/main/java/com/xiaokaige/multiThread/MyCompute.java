package com.xiaokaige.multiThread;

import com.xiaokaige.multiThread.inter.Compute;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zengkai
 * @date 2021/8/2 10:18
 */
public class MyCompute implements Compute {

    private final Map<String, Integer> map = new ConcurrentHashMap<>();

    private final MyComputer myComputer;

    public MyCompute(MyComputer myComputer) {
        this.myComputer = myComputer;
    }


    @Override
    public Integer compute(String param) {
        Integer result = map.get(param);
        if (result == null) {
            int compute = myComputer.compute(param);
            map.put(param, compute);
            return result;
        }
        return result;
    }
}
