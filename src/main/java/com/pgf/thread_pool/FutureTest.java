package com.pgf.thread_pool;

import java.util.concurrent.*;

/**
 * @author pan.gefei
 * @name
 * @date 2022/5/29 16:36
 * @description
 */
public class FutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newSingleThreadExecutor();
        FutureTask<String> futureTask = new FutureTask<>(() -> "666");
        service.submit(futureTask);
        System.out.println(futureTask.get());// get()会阻塞，直到线程执行完任务返回结果
        service.shutdown();
    }
}
