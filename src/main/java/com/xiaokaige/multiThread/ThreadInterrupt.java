package com.xiaokaige.multiThread;

/**
 * @author zengkai
 * @date 2021/7/30 13:45
 */
public class ThreadInterrupt {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        myThread.interrupt();

    }
}


class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
            if (this.isInterrupted()) {
                System.out.println("第一次interrupted" + this.isInterrupted());
                System.out.println("第一次interrupted" + this.isInterrupted());
                break;
            }
        }
        System.out.println("跳出循环");
    }
}