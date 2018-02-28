package thread.synchronizer;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 回环屏障
 * 一组线程全部到达某个状态后才会执行后续任务
 * Created by MelloChan on 2018/3/1.
 */
public class CyclicBarrierDemo {
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
        System.out.println("----- 恢复回环 -------");
    });

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(new Task()).start();
        }
    }/* output:
Thread-0------ 前置业务开始 -------
Thread-2------ 前置业务开始 -------
Thread-1------ 前置业务开始 -------
Thread-0------ 等待其他线程执行前置业务 ------
Thread-1------ 等待其他线程执行前置业务 ------
Thread-2------ 等待其他线程执行前置业务 ------
----- 恢复回环 -------
Thread-2------  前置业务结束,所有线程均到达屏障.后置业务开始 -------
Thread-0------  前置业务结束,所有线程均到达屏障.后置业务开始 -------
Thread-1------  前置业务结束,所有线程均到达屏障.后置业务开始 -------
*/

    static class Task implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "------ 前置业务开始 -------");
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "------ 等待其他线程执行前置业务 ------");
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "------  前置业务结束,所有线程均到达屏障.后置业务开始 -------");
        }
    }
}
