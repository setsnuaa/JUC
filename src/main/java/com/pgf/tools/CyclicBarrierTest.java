package com.pgf.tools;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author pan.gefei
 * @name
 * @date 2022/6/1 19:43
 * @description
 */
public class CyclicBarrierTest {
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(5,
                () -> System.out.println("所有玩家确认，游戏开始"));

        for (int i = 0; i < 10; i++) {
            Thread.sleep(500);
            int finalI = i;
            new Thread(() -> {
                try {
                    System.out.println("玩家 " + finalI + " 进入房间等待..." + (barrier.getNumberWaiting() + 1) + "/5");
                    barrier.await();
                    System.out.println("玩家 " + finalI + " 开始游戏");
                } catch (InterruptedException |BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }
//    结果：
//    玩家 0 进入房间等待...1/5
//    玩家 1 进入房间等待...2/5
//    玩家 2 进入房间等待...3/5
//    玩家 3 进入房间等待...4/5
//    玩家 4 进入房间等待...5/5
//    所有玩家确认，游戏开始
//    玩家 4 开始游戏
//    玩家 0 开始游戏
//    玩家 1 开始游戏
//    玩家 3 开始游戏
//    玩家 2 开始游戏
//    玩家 5 进入房间等待...1/5
//    玩家 6 进入房间等待...2/5
//    玩家 7 进入房间等待...3/5
//    玩家 8 进入房间等待...4/5
//    玩家 9 进入房间等待...5/5
//    所有玩家确认，游戏开始
//    玩家 9 开始游戏
//    玩家 5 开始游戏
//    玩家 7 开始游戏
//    玩家 6 开始游戏
//    玩家 8 开始游戏
}
