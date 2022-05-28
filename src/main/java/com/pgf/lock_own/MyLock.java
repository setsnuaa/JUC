package com.pgf.lock_own;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author pan.gefei
 * @name
 * @date 2022/5/28 19:37
 * @description 独占锁，不可重入
 */
public class MyLock implements Lock {

    private final Sync sync;

    public MyLock() {
        sync = new Sync();
    }

    private static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            if (isHeldExclusively()) return true;
            // cas将锁的状态设置为arg
            if (compareAndSetState(0, arg)) {
                // cas成功代表持有锁了
                System.out.println(Thread.currentThread().getName() + " 成功获取锁");
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if (getState() == 0)
                throw new IllegalMonitorStateException();
            if (isHeldExclusively()) {
                // 持有锁的线程才能release
                System.out.println(Thread.currentThread().getName() + " 释放锁");
                setExclusiveOwnerThread(null);
                setState(0);
                return true;
            }
            return false;
        }

        @Override
        protected boolean isHeldExclusively() {
            return getExclusiveOwnerThread() == Thread.currentThread();
        }

        protected Condition newCondition() {
            return new ConditionObject();
        }
    }

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}

