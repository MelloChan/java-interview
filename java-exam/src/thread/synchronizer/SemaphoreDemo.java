package thread.synchronizer;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 信号量
 * Created by MelloChan on 2018/3/1.
 */
public class SemaphoreDemo {
    /**
    资源数目 另外可实现公平锁 Semaphore(int permits, boolean fair)
     */
    private static Semaphore semaphore = new Semaphore(5);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Task()).start();
        }
    }

    static class Task implements Runnable {
        private static AtomicInteger count=new AtomicInteger();
        @Override
        public void run() {
            try {
                // 获取资源
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + ":"+count.getAndIncrement()+" 获取资源");
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + ":"+count.get()+" 释放资源");
                // 释放资源
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
