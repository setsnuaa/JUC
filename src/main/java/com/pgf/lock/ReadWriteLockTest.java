package com.pgf.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author pan.gefei
 * @name
 * @date 2022/5/24 11:40
 * @description
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        readWriteLock.writeLock().lock();
        readWriteLock.writeLock().lock();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " -> 获取读锁中...");
            readWriteLock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + " -> 获取读锁成功");
            readWriteLock.readLock().unlock();
        }).start();

        System.out.println(Thread.currentThread().getName() + " -> 释放第一重锁");
        readWriteLock.writeLock().unlock();
        System.out.println(Thread.currentThread().getName() + " -> 释放第二重锁");
        readWriteLock.writeLock().unlock();
    }
}
