package com.pgf.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author pan.gefei
 * @name
 * @date 2022/6/1 20:34
 * @description
 */
public class SemaphoreTest {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(2);
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");

        for (int i = 0; i < 3; i++) {
            int finalI = i;
            Thread.sleep(100);
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(formatter.format(new Date()) + "：线程 " + finalI + " 拿到信号量");
                    TimeUnit.SECONDS.sleep(2);
                    semaphore.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
//        结果：
//        20:43:37：线程 0 拿到信号量
//        20:43:37：线程 1 拿到信号量
//        20:43:39：线程 2 拿到信号量
    }
}
