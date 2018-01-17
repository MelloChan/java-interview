package base.reflect;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

/**
 * 反射机制
 * Created by MelloChan on 2018/1/17.
 */
public class ReflectDemo implements Serializable, Cloneable {

    private static final long serialVersionUID = -3714129004501932917L;
    public long id = 1024;

    public static void getClassName() {
        ReflectDemo reflectDemo = new ReflectDemo();
        // 获取 全限定类名  output: base.reflect.ReflectDemo
        System.out.println("output: " + reflectDemo.getClass().getName());
    }

    public static void classReference() throws ClassNotFoundException {
        Class<?>[] classes = new Class[3];
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

    public static void getSuperAndInterface() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("base.reflect.ReflectDemo");
        Class<?> parent = clazz.getSuperclass();
        System.out.println(clazz.getName() + " super -> " + parent.getName());

        Class<?> inters[] = clazz.getInterfaces();
        System.out.println(clazz.getName() + " implements interface -> ");
        for (Class c : inters) {
            System.out.println(c.getName());
        }
    }/*
    output:
    base.reflect.ReflectDemo super -> java.lang.Object
    base.reflect.ReflectDemo implements interface ->
    java.io.Serializable
    java.lang.Cloneable
    */

    public static void getCon() throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<?> clazz = Class.forName("base.reflect.Person");
        Person person = (Person) clazz.newInstance();
        person.setAge(21);
        person.setName("mello");
        // Person{age=21, name='mello'}
        System.out.println(person);

        Constructor<?>[] constructors = clazz.getConstructors();
        /*
        -------------------------------
            java.lang.Integer
            java.lang.String
        -------------------------------
        -------------------------------
            java.lang.String
        -------------------------------
            java.lang.Integer
         */
        for (Constructor con : constructors) {
            print();
            for (Class cl : con.getParameterTypes()) {
                System.out.println(cl.getName());
            }
        }
        print();
        /*
        Person{age=15, name='yuki'}
        Person{age=null, name='null'}
        Person{age=null, name='night'}
        Person{age=22, name='null'}
         */
        person = (Person) constructors[0].newInstance(15, "yuki");
        System.out.println(person);
        person = (Person) constructors[1].newInstance();
        System.out.println(person);
        person = (Person) constructors[2].newInstance("night");
        System.out.println(person);
        person = (Person) constructors[3].newInstance(22);
        System.out.println(person);
    }

    public static void getFields() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("base.reflect.ReflectDemo");
        System.out.println("--------类属性---------");
//        Field[] fields=clazz.getFields(); 只能获取公有字段(包括从其他类继承而来的)
        // 不受修饰符限制 但不能获取从其他类继承而来的字段
        Field[] fields1 = clazz.getDeclaredFields();
        for (Field f : fields1) {
            // 获取字段修饰符
            System.out.println(Modifier.toString(f.getModifiers()));
            // 获取字段类型
            System.out.println(f.getType().getName());
        }/*
        output:
        --------类属性---------
        private static final
        long
        public
        long
        */
    }

    public static void getMethods() {

    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
        getClassName();
        print();
        classReference();
        print();
        getSuperAndInterface();
        print();
        getCon();
        print();
        getFields();
    }

    public static void print() {
        System.out.println("-------------------------------");
    }
}
