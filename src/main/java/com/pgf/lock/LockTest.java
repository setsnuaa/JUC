package com.pgf.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author pan.gefei
 * @name
 * @date 2022/5/23 17:06
 * @description
 */
public class LockTest {
    private static int num;

    public static void main(String[] args) throws InterruptedException {
        //创建一个锁
        Lock lock = new ReentrantLock();
        Runnable action = () -> {
            for (int i = 0; i < 20; i++) {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " -> 第 " + i + " 次对 num 操作");
                num++;
                lock.unlock();
            }
        };
        Thread t1 = new Thread(action);
        Thread t2 = new Thread(action);
        t1.start();
        t2.start();
        Thread.sleep(1000);
        System.out.println("num: " + num);
    }
}
