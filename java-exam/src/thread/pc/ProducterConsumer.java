package thread.pc;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 生产者-消费者模型
 * 基于 notify-wait
 *
 * @author MelloChan
 * @date 2018/4/14
 */
public class ProducterConsumer {
    private final int N;
    private Monitor monitor;

    public ProducterConsumer(int size) {
        N = size;
        monitor = new Monitor();
    }

    public static void main(String[] args) {
        ProducterConsumer pc = new ProducterConsumer(20);
        pc.new Producer().start();
        pc.new Consumer().start();
    }

    class Producer extends Thread {
        @Override
        public void run() {
            int val;
            while (true) {
                val = product();
                monitor.insert(val);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private int product() {
            int val = new Random().nextInt(N);
            System.out.println("-- 生产:" + val + " -- ");
            return val;
        }
    }

    class Consumer extends Thread {
        @Override
        public void run() {
            int val;
            while (true) {
                val = monitor.remove();
                consume(val);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void consume(int val) {
            System.out.println(" -- 消费:" + val + " -- ");
        }
    }

    class Monitor {
        private int[] buffer = new int[N];
        private int count = 0, head = 0, tail = 0;

        public synchronized void insert(int val) {
            if (count == N) {
                goToSleep();
            }
            buffer[tail] = val;
            tail = (tail + 1) % N;
            count++;
            if (count == 1) {
                notify();
            }
        }

        public synchronized int remove() {
            if (count == 0) {
                goToSleep();
            }
            int val = buffer[head];
            head = (head + 1) % N;
            count--;
            if (count == N - 1) {
                notify();
            }
            return val;
        }

        private void goToSleep() {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("-- 休眠中 等待唤醒 --");
            }
        }
    }
}
