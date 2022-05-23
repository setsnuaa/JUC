package com.pgf.parallel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author pan.gefei
 * @name
 * @date 2022/5/23 15:40
 * @description
 */
public class ParallelTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        list
                .parallelStream()
                .forEach(i -> System.out.println(Thread.currentThread().getName() + " -> " + i));
    }
}
