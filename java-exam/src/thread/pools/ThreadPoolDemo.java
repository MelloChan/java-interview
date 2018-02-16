package thread.pools;

import java.util.concurrent.*;

/**
 * 线程池简单demo
 * Created by MelloChan on 2018/2/14.
 */
public class ThreadPoolDemo {

    private static ExecutorService service= Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException {
        for (int i = 0; i < 10; i++) {
            service.execute(new Task01());
        }
        Future<String>future=service.submit(new Task02());
        try {
            String result = future.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Task01 implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }

    static class Task02 implements Callable<String>{

        @Override
        public String call(){
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Thread.currentThread().getName();
        }
    }
}
