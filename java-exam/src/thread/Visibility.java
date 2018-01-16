package thread;

/**
 * 可见性 执行非原子操作无法保证执行结果
 * Created by MelloChan on 2018/1/16.
 */
public class Visibility {
    private static volatile int race=0;

    public static void inc(){
        race++;
    }

    private static final int THREADS_COUNT=20;

    public static void main(String[] args) {
        for (int i = 0; i < THREADS_COUNT; i++) {
            new Thread(()->{
                for (int j = 0; j < 100; j++) {
                    inc();
                }
            }).start();
        }
        System.out.println(race);
    }
}

