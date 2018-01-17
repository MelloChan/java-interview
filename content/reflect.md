### 反射

#### 定义

反射机制使得你能在运行时发现和使用类的字段以及方法,也就是能获取到任何类的类型信息

#### 作用  

①运行时判断任意一个实例所属的类;  
②运行时构造任意一个类的实例;  
③运行时判断任意一个类所具有的字段和方法;  
④运行时调用一个类实例的方法;  
⑤动态代理.  

#### [相关api](https://github.com/MelloChan/java-interview/blob/master/java-exam/src/base/reflect)

获取全限定类名:  
```
public static void getClassName() {
        ReflectDemo reflectDemo = new ReflectDemo();
        // 获取 全限定类名  output: base.reflect.ReflectDemo
        System.out.println("output: " + reflectDemo.getClass().getName());
    }
```

创建class对象引用:
```
public static void classReference() throws ClassNotFoundException {
        Class<?>[] classes = new Class[3];
        classes[0] = Class.forName("base.reflect.ReflectDemo"); // ①
        classes[1] = ReflectDemo.class;   // ②
        classes[2] = new ReflectDemo().getClass();  // ③
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
```
注意:[第二种方式](https://github.com/MelloChan/java-interview/blob/master/java-exam/src/base/reflect/ClassReference.java)不会引发类的初始化.

获取对象的父类以及接口
```
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
```
获取类的全部构造方法
```
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
```

#### 应用