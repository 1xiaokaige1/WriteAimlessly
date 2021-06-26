package com.xiaokaige;

import com.xiaokaige.entity.StudentDO;

import java.util.concurrent.*;

/**
 * @author zengkai
 * @date 2021/6/26 9:59
 */
public class StreamAsyTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Future<Object> future = executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                TimeUnit.SECONDS.sleep(5);
                return StudentDO
                        .builder()
                        .id(2L)
                        .name("xiaozeng")
                        .address("深圳市龙岗区")
                        .age(24)
                        .build();

            }
        });
        System.out.println("我没有等待20S");
        StudentDO studentDO = (StudentDO) future.get();
        System.out.println(studentDO);
    }


}
