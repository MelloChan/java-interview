package thread.pools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池简单demo
 * Created by MelloChan on 2018/2/14.
 */
public class ThreadPoolDemo {

    private static ExecutorService service= Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            service.execute(new Task());
        }
    }

    static class Task implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }
}
