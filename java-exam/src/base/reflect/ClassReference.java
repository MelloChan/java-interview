package base.reflect;


/**
 * 反射的三种实例化方式
 * Created by MelloChan on 2018/1/17.
 */
public class ClassReference {
    public static void main(String[] args) throws ClassNotFoundException {
        Class[] classes = new Class[3];
        classes[0] = Class.forName("base.reflect.TypeOne");
        // 不会引发初始化
        classes[1] = TypeTwo.class;
        classes[2] = new TypeThree().getClass();
    }
}/*
output:
TypeOne Static Block
TypeThree Static Block
*/

class TypeOne{
    static {
        System.out.println("TypeOne Static Block");
    }
}
class TypeTwo{
    static {
        System.out.println("TypeTwo Static Block");
    }
}
class TypeThree{
    static {
        System.out.println("TypeThree Static Block");
    }
}
