package com.xiaokaige.lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author zengkai
 * @date 2021/6/18 10:48
 */
public class MyLock extends AbstractQueuedSynchronizer {

    @Override
    protected boolean tryAcquire(int arg) {
        return super.tryAcquire(arg);
    }

    @Override
    protected boolean tryRelease(int arg) {
        return super.tryRelease(arg);
    }
}
