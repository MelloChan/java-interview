### 反射

#### 定义

反射机制使得你能在运行时发现和使用类的字段以及方法,也就是能获取到任何类的类型信息

#### 作用  

①运行时判断任意一个实例所属的类;  
②运行时构造任意一个类的实例;  
③运行时判断任意一个类所具有的字段和方法;  
④运行时调用一个类实例的方法;  
⑤动态代理.  

#### 相关api

获取全限定类名
```
public static void getClassName() {
        ReflectDemo reflectDemo = new ReflectDemo();
        // 获取 全限定类名  output: base.reflect.ReflectDemo
        System.out.println("output: " + reflectDemo.getClass().getName());
    }
```

#### 应用