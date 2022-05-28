package com.pgf.lock_own;

import java.util.concurrent.locks.Condition;

/**
 * @author pan.gefei
 * @name
 * @date 2022/5/28 19:49
 * @description
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        MyLock lock = new MyLock();
        Condition condition = lock.newCondition();

        lock.lock();

        System.out.println(Thread.currentThread().getName() + " 进入临界区");
        new Thread(() -> {
            lock.lock();

            System.out.println(Thread.currentThread().getName() + " 进入临界区");
            // 唤醒 main
            System.out.println(Thread.currentThread().getName() + " 唤醒 main");
            condition.signal();

            lock.unlock();
        }).start();
        // wait之后释放锁
        condition.await();

        lock.unlock();
    }
//    结果：
//    main 成功获取锁
//    main 进入临界区
//    main 释放锁
//    Thread-0 成功获取锁
//    Thread-0 进入临界区
//    Thread-0 唤醒 main
//    Thread-0 释放锁
//    main 成功获取锁
//    main 释放锁
}

