package base.reflect;

/**
 * 反射机制
 * Created by MelloChan on 2018/1/17.
 */
public class ReflectDemo {
    public static void getClassName() {
        ReflectDemo reflectDemo = new ReflectDemo();
        // 获取 全限定类名  output: base.reflect.ReflectDemo
        System.out.println("output: " + reflectDemo.getClass().getName());
    }

    public static void main(String[] args) {
        getClassName();
    }
}
