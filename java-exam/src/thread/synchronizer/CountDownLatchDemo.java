package thread.synchronizer;

import java.util.concurrent.CountDownLatch;

/**
 * 闭锁
 * 某条线程需要等待其他所有线程准备完毕后才能执行.
 * 构造器中允许传入一个整型值 以此决定等待的线程数
 * Created by MelloChan on 2018/2/28.
 */
public class CountDownLatchDemo {
    static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) {
        new Thread(new Task()).start();

        new Thread(() -> {
            try {
                // 锁为0 唤醒线程
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "------ 其他后续业务 ------");
        }).start();

        new Thread(new Task()).start();
    }

    static class Task implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " ------- 某些业务 -------");
            // 锁 -1
            countDownLatch.countDown();
        }
    }
}

