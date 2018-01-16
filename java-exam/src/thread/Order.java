package thread;

/**
 * JMM 有序性
 * Created by MelloChan on 2018/1/16.
 */
public class Order {
    private static boolean flag;
    private static int value;


    public static void main(String[] args) {
        new Thread(()->{
            while (!flag)Thread.yield();
            System.out.println(value);
        }).start();
        value=2;
        flag=true;
    }
}
