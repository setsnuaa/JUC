package com.pgf.tools;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author pan.gefei
 * @name
 * @date 2022/5/31 15:39
 * @description
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    Thread.sleep(1000 + new Random().nextInt() % 1000);
                    System.out.println("子任务 " + finalI + " 执行完成");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                latch.countDown();
            }).start();
        }

        // 线程会阻塞在await()
        latch.await();
        System.out.println("所有子任务执行完成");
    }
}
