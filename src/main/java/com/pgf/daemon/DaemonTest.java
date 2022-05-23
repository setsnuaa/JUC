package com.pgf.daemon;

/**
 * @author pan.gefei
 * @name
 * @date 2022/5/23 15:14
 * @description
 */
public class DaemonTest {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            while (true) {
                try {
                    System.out.println(Thread.currentThread().getName() + "正在运行");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t.setDaemon(true);
        t.start();
        // main线程睡眠5次，每次1s，当它运行结束后，上面那个线程也会结束
        // 如果不设置为守护线程，main会等其他线程结束，那么就会一直打印了
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
