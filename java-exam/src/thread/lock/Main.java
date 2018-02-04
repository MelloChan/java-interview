package thread.lock;

/**
 * Created by MelloChan on 2018/2/3.
 */
public class Main {
    public static void main(String[] args) {
        SynchronizedDemo demo=new SynchronizedDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(demo::methodB).start();
        }
    }
}
