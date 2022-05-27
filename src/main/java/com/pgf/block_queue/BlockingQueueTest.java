package com.pgf.block_queue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author pan.gefei
 * @name
 * @date 2022/5/26 17:47
 * @description
 */
public class BlockingQueueTest {
    public static void main(String[] args) {
        BlockingQueue<Object> queue = new ArrayBlockingQueue<>(1);

        Runnable cook = () -> {
            while (true) {
                try {
                    String name = Thread.currentThread().getName();
                    System.err.println(time() + "厨师 " + name + " 正在准备餐品...");
                    TimeUnit.SECONDS.sleep(3);
                    System.err.println(time() + "厨师 " + name + " 已出餐！");
                    queue.put(new Object());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        };

        Runnable consumer = () -> {
            while (true) {
                try {
                    String name = Thread.currentThread().getName();
                    System.out.println(time() + "顾客 " + name + " 正在等待餐品...");
                    queue.take();
                    System.out.println(time() + "顾客 " + name + " 取餐");
                    TimeUnit.SECONDS.sleep(4);
                    System.out.println(time() + "顾客 " + name + " 已经吃完");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        };

        for (int i = 0; i < 2; i++)
            new Thread(cook, "Cook-" + i).start();
        for (int i = 0; i < 3; i++) {
            new Thread(consumer, "Consumer-" + i).start();
        }
    }

    private static String time() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return "[" + format.format(new Date()) + "]";
    }
}
// 结果
//    [18:00:20]顾客 Consumer-1 正在等待餐品...
//    [18:00:20]顾客 Consumer-2 正在等待餐品...
//    [18:00:20]顾客 Consumer-0 正在等待餐品...
//    [18:00:20]厨师 Cook-0 正在准备餐品...
//    [18:00:20]厨师 Cook-1 正在准备餐品...
//    [18:00:23]厨师 Cook-1 已出餐！
//    [18:00:23]厨师 Cook-0 已出餐！
//    [18:00:23]厨师 Cook-1 正在准备餐品...
//    [18:00:23]厨师 Cook-0 正在准备餐品...
//    [18:00:23]顾客 Consumer-1 取餐
//    [18:00:23]顾客 Consumer-2 取餐
//    [18:00:26]厨师 Cook-1 已出餐！
//    [18:00:26]厨师 Cook-1 正在准备餐品...
//    [18:00:26]厨师 Cook-0 已出餐！
//    [18:00:26]厨师 Cook-0 正在准备餐品...
//    [18:00:26]顾客 Consumer-0 取餐
//    [18:00:27]顾客 Consumer-2 已经吃完
//    [18:00:27]顾客 Consumer-1 已经吃完