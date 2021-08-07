package com.xiaokaige.thread;

import java.util.concurrent.*;

/**
 * @author zengkai
 * @date 2021/8/4 19:00
 */
public class Func {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        Task task = new Task();
        Future<Integer> futureTask = threadPool.submit(task);
       /* boolean flag = futureTask.cancel(true);
        System.out.println("flag = " + flag);
        boolean cancelFlag = futureTask.isCancelled();
        System.out.println("cancelFlag = " + cancelFlag);
        boolean doneFlag = futureTask.isDone();
        System.out.println("doneFlag = " + doneFlag);*/
        Integer result = futureTask.get();
        System.out.println("result = " + result);
    }
}
