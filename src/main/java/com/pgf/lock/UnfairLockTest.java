package com.pgf.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author pan.gefei
 * @name
 * @date 2022/5/23 19:30
 * @description
 */
public class UnfairLockTest {
    public static void main(String[] args) throws InterruptedException {
        Lock unfairLock = new ReentrantLock(false);

        Runnable action = () -> {
            System.out.println(Thread.currentThread().getName() + " 尝试获得锁");
            unfairLock.lock();
            System.out.println(Thread.currentThread().getName() + " 获得锁");
            try {
                // 睡0.5s，保证后面的线程进入等待队列
                Thread.sleep(150);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            unfairLock.unlock();
        };

        for (int i = 0; i < 5; i++) {
            new Thread(action, "t" + i).start();
            // 睡0.1s，保证先创建的线程先进入等待队列
            //Thread.sleep(100);
        }
    }
}
