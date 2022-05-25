package com.pgf.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author pan.gefei
 * @name
 * @date 2022/5/25 11:50
 * @description
 */
public class AtomicTest {
    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger(0);
        System.out.println(integer.compareAndSet(10, 20));// false
        System.out.println(integer.compareAndSet(0, 10));// true
        System.out.println(integer.get());// 10
    }
}
