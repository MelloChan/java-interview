package thread.synchronizer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 闭锁
 * 某条线程需要等待其他所有线程准备完毕后才能执行.
 * 构造器中允许传入一个整型值 以此决定等待的线程数
 * Created by MelloChan on 2018/2/28.
 */
public class CountDownLatchDemo {
    static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Task()).start();
        new Thread(new Task()).start();

        System.out.println(Thread.currentThread().getName() + "------ 等待其他线程业务执行完毕 -------");
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+" ------- 其他线程业务已执行完毕 ---------");
        System.out.println(Thread.currentThread().getName()+" -------- 执行main主线程业务 --------");
    }/* output:
main------ 等待其他线程业务执行完毕 -------
Thread-1 ------- 某些业务 -------
Thread-0 ------- 某些业务 -------
Thread-1 ------- 业务完毕 -------
Thread-0 ------- 业务完毕 -------
main ------- 其他线程业务已执行完毕 ---------
main -------- 执行main主线程业务 --------
*/

    static class Task implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " ------- 某些业务 -------");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " ------- 业务完毕 -------");
            // 锁 -1
            countDownLatch.countDown();
        }
    }
}

