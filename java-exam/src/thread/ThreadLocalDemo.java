package thread;

/**
 * ThreadLocal
 * Created by MelloChan on 2018/2/5.
 */
public class ThreadLocalDemo {
    private static final ThreadLocal<Integer> value = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new MyThread(i)).start();
        }
    }

    static class MyThread implements Runnable {
        private int index;

        public MyThread(int index) {
            this.index = index;
        }

        @Override
        public void run() {
            System.out.println("Thread-" + index + " : " + value.get());
            for (int i = 0; i < 10; i++) {
                value.set(value.get() + 1);
            }
            System.out.println("Thread-" + index + " : " + value.get());
        }
    }
}
