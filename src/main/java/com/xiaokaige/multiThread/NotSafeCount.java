package com.xiaokaige.multiThread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zengkai
 * @date 2021/7/24 14:31
 */
public class NotSafeCount {
    public static void main(String[] args) {
        Count count = new Count();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                int value = count.getNext();
                System.out.println("valueOne:" + value);
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                int value = count.getNext();
                System.out.println("valueTwo:" + value);
            }
        }).start();

    }
}


class Count {
    private AtomicInteger value = new AtomicInteger(0);

    public int getNext() {
        return value.incrementAndGet();
    }
}
