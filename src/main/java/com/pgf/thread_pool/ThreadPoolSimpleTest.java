package com.pgf.thread_pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author pan.gefei
 * @name
 * @date 2022/5/25 20:43
 * @description
 */
public class ThreadPoolSimpleTest {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,
                4,
                3,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2));

        for (int i = 0; i < 6; i++) {
            int finalI = i;
            executor.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " 开始执行 " + finalI);
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + " 已结束 " + finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        TimeUnit.SECONDS.sleep(1);
        System.out.println("线程池中线程数量：" + executor.getPoolSize());
        TimeUnit.SECONDS.sleep(5);
        System.out.println("线程池中线程数量：" + executor.getPoolSize());

        executor.shutdownNow();

        // 结果
//        pool-1-thread-1 开始执行 0
//        pool-1-thread-4 开始执行 5
//        pool-1-thread-3 开始执行 4
//        pool-1-thread-2 开始执行 1
//        pool-1-thread-3 已结束 4
//        线程池中线程数量：4
//        pool-1-thread-1 已结束 0
//        pool-1-thread-2 已结束 1
//        pool-1-thread-4 已结束 5
//        pool-1-thread-1 开始执行 3
//        pool-1-thread-3 开始执行 2
//        pool-1-thread-1 已结束 3
//        pool-1-thread-3 已结束 2
//        线程池中线程数量：2
    }
}
