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

    public static void classReference() throws ClassNotFoundException {
        Class[] classes = new Class[3];
        classes[0] = Class.forName("base.reflect.ReflectDemo");
        classes[1] = ReflectDemo.class;
        classes[2] = new ReflectDemo().getClass();
        for (int i = 0; i < 3; i++) {
            System.out.println("ClassName : " + classes[i]);
        }
        /*
        output:
        ClassName : class base.reflect.ReflectDemo
        ClassName : class base.reflect.ReflectDemo
        ClassName : class base.reflect.ReflectDemo
         */
    }

    public static void main(String[] args) throws ClassNotFoundException {
        getClassName();
        classReference();
    }
}
