package com.pgf.happens_before;

/**
 * @author pan.gefei
 * @name
 * @date 2022/5/23 16:15
 * @description
 */
public class HappensBeforeTest {
    private static int a;
    private static int b;

    public static void main(String[] args) {
        a = 10;
        b = a + 1;
        new Thread(() -> {
            if (b > 10) System.out.println(a);
        }).start();
    }
}
