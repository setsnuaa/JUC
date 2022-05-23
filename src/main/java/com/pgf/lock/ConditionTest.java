package com.pgf.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author pan.gefei
 * @name
 * @date 2022/5/23 17:20
 * @description
 */
public class ConditionTest {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(() -> {
            lock.lock();
            System.out.println("线程1进入等待状态");
            try {
                condition.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("线程1等待结束");
            lock.unlock();
        }).start();
        Thread.sleep(100);
        new Thread(() -> {
            lock.lock();
            System.out.println("线程2唤醒其他等待线程");
            condition.signal();
            System.out.println("线程2运行结束");
            lock.unlock();
        }).start();
    }
}
