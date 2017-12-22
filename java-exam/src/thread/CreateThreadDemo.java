package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建线程的方式
 * Created by MelloChan on 2017/12/22.
 */
public class CreateThreadDemo {
    public static void main(String[] args) {
        CreateThreadDemo ctd = new CreateThreadDemo();
        Thread t1 = ctd.new ExtendsThread();
        t1.start();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ": Implements Runnable!");
        }).start();
        ctd.new ThreadPools().pools();
    }

    class ExtendsThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": Extends Thread!");
        }
    }

    class ImplementsRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": Implements Runnable!");
        }
    }

    class ImplementsCallable implements Callable {

        @Override
        public Object call() throws Exception {
            System.out.println(Thread.currentThread().getName() + ": Implements Callable!");
            return " Call!";
        }
    }

    class ThreadPools {
        ExecutorService exc = Executors.newCachedThreadPool();

        public void pools() {
            exc.execute(new ImplementsRunnable());
            exc.execute(new ExtendsThread());
        }
    }
}
