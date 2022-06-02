package com.pgf.tools;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author pan.gefei
 * @name
 * @date 2022/6/2 16:59
 * @description
 */
public class ForkJoinTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool threadPool = new ForkJoinPool();
        System.out.println(threadPool.submit(new SubTask(1, 1000)).get());
    }

    private static class SubTask extends RecursiveTask<Integer> {
        private final int start;
        private final int end;

        public SubTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            if (end - start > 125) {
                // 有点像递归，分成两部分，分别执行，然后汇总
                SubTask subTask1 = new SubTask(start, (start + end) / 2);
                subTask1.fork();
                SubTask subTask2 = new SubTask((start + end) / 2 + 1, end);
                subTask2.fork();
                return subTask1.join() + subTask2.join();
            } else {
                System.out.println(Thread.currentThread().getName() + " 开始计算" + start + "-" + end + " 的值");
                int res = 0;
                for (int i = start; i <= end; i++) {
                    res += i;
                }
                return res;
            }
        }
    }
//    结果：
//    ForkJoinPool-1-worker-2 开始计算1-125 的值
//    ForkJoinPool-1-worker-15 开始计算126-250 的值
//    ForkJoinPool-1-worker-13 开始计算251-375 的值
//    ForkJoinPool-1-worker-6 开始计算751-875 的值
//    ForkJoinPool-1-worker-9 开始计算376-500 的值
//    ForkJoinPool-1-worker-15 开始计算876-1000 的值
//    ForkJoinPool-1-worker-13 开始计算501-625 的值
//    ForkJoinPool-1-worker-1 开始计算626-750 的值
//    500500
}
