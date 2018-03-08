package jvm;

/**
 * 创建两个不同的类 其使用的类加载器实例时一致的
 * Created by MelloChan on 2018/3/8.
 */
public class ClassLoaderDemo {
    public static void main(String[] args) {
        ClassLoader classLoader=ClassLoaderDemo.class.getClassLoader();
        ClassLoader classLoader1=Demo.class.getClassLoader();
        while (classLoader!=null){
            System.out.println(classLoader.toString());
            classLoader=classLoader.getParent();
        }
        while (classLoader1!=null){
            System.out.println(classLoader1.toString());
            classLoader1=classLoader1.getParent();
        }
//        ClassLoader classLoader2=new ClassLoaderDemo().getClass().getClassLoader();
//        ClassLoader classLoader3=new Demo().getClass().getClassLoader();
//        System.out.println(classLoader2.toString()+"\n"+classLoader3.toString());
    }
}/**
 output:
sun.misc.Launcher$AppClassLoader@18b4aac2
sun.misc.Launcher$ExtClassLoader@4554617c
sun.misc.Launcher$AppClassLoader@18b4aac2
sun.misc.Launcher$ExtClassLoader@4554617c
*/
class Demo{
    static int value=1;
    static {
        System.out.println("value:"+value);
    }
}