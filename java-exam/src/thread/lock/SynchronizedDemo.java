package thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized demo  可重入内置锁
 * Created by MelloChan on 2018/2/3.
 */
public class SynchronizedDemo{
    private final Object object = new Object();
    private final Lock lock=new ReentrantLock();

    public void methodA() {
        synchronized (object) {
            System.out.println(Thread.currentThread().getName());
            System.out.println("-----START------");
            doSomething();
            System.out.println("-----END-------");
        }
    }

    public void methodB() {
        lock.lock();
        System.out.println(Thread.currentThread().getName());
        System.out.println("-----START------");
        doSomething();
        System.out.println("-----END-------");
        lock.unlock();
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
