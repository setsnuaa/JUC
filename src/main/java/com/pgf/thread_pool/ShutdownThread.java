package com.pgf.thread_pool;

/**
 * @author pan.gefei
 * @name
 * @date 2022/5/30 16:46
 * @description
 */
public class ShutdownThread {
    public static void main(String[] args) throws InterruptedException {
        RunImpl run = new RunImpl();
        new Thread(run).start();
        Thread.sleep(1000);
        run.setFlag(false);// 终止线程
    }

    public static class RunImpl implements Runnable {
        private boolean flag = true;

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        @Override
        public void run() {
            for (; ; ) {
                if (flag) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("线程在运行");
                } else break;
            }
            System.out.println("跳出循环，线程结束");
        }
    }
}
