package com.pgf.thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author pan.gefei
 * @name
 * @date 2022/5/26 12:21
 * @description
 */
public class ExecutorsTest {
    public static void main(String[] args) {
        // 固定数量
        ExecutorService service = Executors.newFixedThreadPool(5);

        // 单线程
        // 这种方式是被代理了的，用newFixedThreadPool()这个方法也可以创建一个单线程的线程池
        // 但是可以在运行过程中修改线程数量，不安全
        // 所以用了一个内部类来代理，这个内部类没有实现修改线程数量的方法，所以安全
        ExecutorService singleService = Executors.newSingleThreadExecutor();

        // 全是非核心线程，来一个任务创建一个，60s之后没有任务就销毁
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    }
}
