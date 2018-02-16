package thread.pools;

import java.util.concurrent.*;

/**
 * 线程池简单demo
 * Created by MelloChan on 2018/2/14.
 */
public class ThreadPoolDemo {

    private static ExecutorService service= Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        for (int i = 0; i < 10; i++) {
//            service.execute(new Task());
            System.out.println(service.submit(new FutureTask()).get());
        }

    }

    static class Task implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }

    static class FutureTask implements Callable<String>{

        @Override
        public String call() throws Exception {
            return Thread.currentThread().getName();
        }
    }
}
