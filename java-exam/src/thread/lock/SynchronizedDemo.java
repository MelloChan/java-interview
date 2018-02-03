package thread.lock;

import java.util.concurrent.TimeUnit;

/**
 * synchronized demo
 * Created by MelloChan on 2018/2/3.
 */
public class SynchronizedDemo{
    private final Object object = new Object();

    public void methodA() {
        synchronized (object) {
            System.out.println(Thread.currentThread().getName());
            System.out.println("-----START------");
            doSomething();
            System.out.println("-----END-------");
        }
    }

    public static void doSomething() {
        System.out.println("------doSomething------");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
