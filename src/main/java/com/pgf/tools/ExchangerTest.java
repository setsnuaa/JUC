package com.pgf.tools;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * @author pan.gefei
 * @name
 * @date 2022/6/1 20:54
 * @description
 */
public class ExchangerTest {
    public static void main(String[] args) throws InterruptedException {
        Exchanger<String> exchanger = new Exchanger<>();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + exchanger.exchange(" thread"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(Thread.currentThread().getName() + exchanger.exchange(" main"));
    }
//    main thread
//    Thread-0 main
}
