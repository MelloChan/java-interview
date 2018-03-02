package base.features;

/**
 * 默认方法
 * Created by MelloChan on 2018/2/20.
 */
public interface DefaultMethod {
    default long add(long x, long y) {
        return x + y;
    }

    static void doSomething(){
        System.out.println("--- doSomething ---");
    }
    static class Main{
        public static void main(String[] args) {
            System.out.println(new DefaultMethod(){
            }.add(100L,200L));
            DefaultMethod.doSomething();
        }
    }
}/* output:
300
--- doSomething ---
*/
