package com.pgf.thread_pool;

import java.util.concurrent.*;

/**
 * @author pan.gefei
 * @name
 * @date 2022/5/29 17:05
 * @description
 */
public class TimeTask {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);

        scheduledThreadPool.scheduleAtFixedRate(() -> System.out.println("666"),3,1,TimeUnit.SECONDS);

        //scheduledThreadPool.shutdown();
    }
}
