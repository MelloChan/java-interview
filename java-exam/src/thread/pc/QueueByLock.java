package thread.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 基于API级别的可重入锁(条件变量)实现生产者消费者队列
 * @author MelloChan
 * @date 2018/4/1
 */
public class QueueByLock<T> {
    private final T[] items;
    private final Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();
    private int head, tail, count;

    public QueueByLock(int maxSize) {
        items = (T[]) new Object[maxSize];
    }

    public QueueByLock() {
        this(10);
    }

    public void put(T t) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length) {
                notFull.await();
            }
            items[tail] = t;
            if (++tail == items.length) {
                tail = 0;
            }
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public T task() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.wait();
            }
            T o = items[head];
            items[head] = null;
            if (++head == items.length) {
                head = 0;
            }
            --count;
            notFull.signal();
            return o;
        } finally {
            lock.unlock();
        }
    }
}
