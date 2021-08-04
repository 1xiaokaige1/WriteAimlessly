package com.xiaokaige.multiThread;

import com.xiaokaige.multiThread.inter.Compute;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author zengkai
 * @date 2021/8/2 10:29
 */
public class MyFutureCompute implements Compute {

    private final Map<String, Future<Integer>> map = new ConcurrentHashMap<>();

    private final MyCompute myCompute;

    public MyFutureCompute(MyCompute myCompute) {
        this.myCompute = myCompute;
    }

    @Override
    public Integer compute(String param) {
        Future<Integer> future = map.get(param);
        if (future == null) {
            Callable<Integer> callFunction = new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return myCompute.compute(param);
                }
            };
            FutureTask<Integer> futureTask = new FutureTask<>(callFunction);
            future = futureTask;
            map.put(param, future);
            futureTask.run();
        }
        try {
            return future.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
