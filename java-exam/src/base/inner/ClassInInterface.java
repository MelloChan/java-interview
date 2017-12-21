package base.inner;

/**
 * 接口中可放置嵌套类
 * Created by MelloChan on 2017/12/21.
 */
public interface ClassInInterface {
    void howdy();

    class Test implements ClassInInterface {

        @Override
        public void howdy() {
            System.out.println("HowDY!");
        }

        public static void main(String[] args) {
            new Test().howdy();
        }
    }

    default Test test() {
        return new Test();
    }
}
