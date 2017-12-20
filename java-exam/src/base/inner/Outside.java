package base.inner;

/**
 * 内部类Demo
 * Created by MelloChan on 2017/12/20.
 */
public class Outside {
    private int outsideValue;
    private static int id;

    class Inner {
        private int value=1;

        public int value() {
            System.out.println("outside:"+outsideValue);
            System.out.println("inner:"+new Inner().value);
            return value;
        }

        /**
         * 通过 .this 生成外部类对象的引用
         *
         * @return
         */
        public Outside outer() {
            return Outside.this;
        }

        public Inner inner() {
            return build();
        }
    }

    /**
     * 通过一个方法来获取内部类对象
     *
     * @return
     */
    public Inner build() {
        return new Inner();
    }

    public static void main(String[] args) {
        /*
        提供一个方法来获取外部类
         */
        Inner inner = new Outside().build();
        System.out.println(inner.value());
        /*
         .new 获取内部类对象 前提是需要先new一个外部类对象
         */
        Outside outside = new Outside();
        Outside.Inner inner1 = outside.new Inner();
        System.out.println(inner1.outer());
        System.out.println(inner1.inner());
    }
}
