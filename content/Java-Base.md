### Java基础

- 四大基本特性  

封装/继承/多态/抽象  

封装: 使用不同的访问修饰符,让类的属性与方法只让可信的类或者对象实例操作,
对不可信的进行封装隐藏.分为属性封装与方法封装.  

继承: 代码复用分为继承(is-a)与组合(has-a,具有更高的灵活性).对有着高度共同特性的的多列事物进行在抽象成一个类
.这个类称为父类,子类通过extends关键字继承父类抽象的共性.  

多态: 编译期调用或运行时动态确定调用的代码.方法的重载与重写(覆盖)体现了这两点.  

抽象: 将现实世界的某一类事物进行属性与方法提取,然后用程序代码实现,类或接口体现了抽象.
抽象包括两方面:①数据抽象,即对象属性的封装 ②过程抽象,即对象的行为特征.   

- 重载与重写的区别    

重载:重载的方法具有相同的方法名,其区别在于参数列表的不同,访问修饰符与返回值可以不同,发生在编译期.       

重写:运行时确定调用的代码.发生在父子类中,方法名与参数列表必须相同,返回值可以相同或者协变(返回类型小于等于父类的返回类型).抛出的异常小于等于父类,访问修饰符必须大于等于父类.
被private修饰的方法不会被重写.  

- 访问控制符  

private default protected public 访问权限依次递增.  

private:私有的,只允许自身访问;  

default:默认包访问修饰符,不需要关键字.只允许自身以及同包的类访问;  

protected:受保护的.只允许自身以及其子类访问;  

public:公有的.允许所有的类对其访问.    

注意点:在JDK1.8后推出了default关键字,使用在接口中,让接口也能实现方法.  

- [抽象类与接口](https://github.com/MelloChan/java-interview/blob/master/java-exam/src/base/abstraction)  

语法层次:抽象(abstract)允许普通与抽象方法并存(当然也可以不存在抽象方法);接口(interface)方法默认为public abstract,字段默认为private static final,
在JDK1.8中允许有default关键字修饰的普通方法;  

设计层次:抽象类是对类抽象,而接口则是行为抽象.抽象类是对客观世界拥有共性特征的事物进行整体抽象,是自底向上的,而接口则是局部行为抽象.
是自顶向下的.因此抽象类表现的是一种is-a关心,接口表现的则是like-a.  

另外,抽象类可以继承抽象类,也能实现接口,接口能够继承接口,而他们都不可被实例化.  

- [类初始化顺序](https://github.com/MelloChan/java-interview/blob/master/java-exam/src/base/InitDemo.java)  

首先是静态属性,其次是普通属性,最后则是构造方法;

父子类中,首先初始化父类静态属性/块,然后是子类静态属性/块,其次是父类普通属性/块,构造方法,然后才是子类的普通属性/块,构造方法.

- hashCode & equals  

hashCode相等,值不一定相等,值相等则hashCode一定相等.因此hashCode方法一定要配合equals方法重写.
参考Effective Java 第九条.

- [== & equals](https://github.com/MelloChan/java-interview/blob/master/java-exam/src/base/Compare.java)  

对于基本类型的值比较使用 == 即可,但基于引用类型(诸如通过new String())之类的对象实例进行比较,则需要使用
equals方法,单纯使用 == 将会对引用的地址进行比较,而不是值.  

- this  

①作为对象实例,被默认传入普通方法中.一般用作返回当前对象的引用(如链式调用);    
②在构造器中调用当前类的其他构造器(要放在语句的第一条,且只有这一个);    
③调用对象属性.  

- 关于this() & super()  

 ①有显式this()调用的构造器就会抑制掉该构造器里隐式的super()调用；  
 ②没有显式this()调用的构造器则会得到隐式的super()调用。  
 -- R大  
 
- static      

目的:  
①只想为某特定区域分配单一存储空间;  
②不与实例对象关联的方法.     

static(静态)方法:没有this的方法,不可调用普通方法以及属性.
被static所修饰的方法或属性是类级别的(在加载时就会存入内存中,因此使用其所修饰的方法前期是类被加载到JVM中),即共享的,不需要实例化即可调用.  


- 基本类型 & 包装类  

基本类型:boolean(无指定) byte(8-bit) char(16-bit) short(16-bit) int(32-bit) long(64-bit) 
float(32-bit) double(64-bit)  

包装类:Boolean Byte Character Short Integer Long Float Double BigInteger、BigDecimal    
包装类拥有自动拆箱/装箱的功能,且提供了各种方便的api,最重要的是让各种数据类型间的转化更加方便.  

- String  

String类型,最常用的数据类型之一.该类被修饰为final(不可被继承).  
与StringBuffer、StringBuilder区别:  
①可变性:String使用 private final char value[];来存储字符,因此实例化的对象不可变的.而后者继承自
AbstractStringBuilder,使用char[] value;存储字符,因此是可变的;    

②线程安全性:String中的对象可以说是常量,因此是线程安全的.而StringBuffer虽然可变,但其提供的api都通过synchronized关键字进行方法修饰,
因此也是线程安全的.而StringBuilder是非线程安全的;  

③性能:对String的改变都会重新创建新的对象,然后将指针指向新引用地址,后者是对本身进行修改.  

- 泛型  

简单来讲即"参数化类型".创建集合时就确定集合元素的类型,使集合只能保存指定类型的元素(编译期类型安全确认),避免强制转换.
泛型在编译后类型会被擦除,泛型Java代码将转换为普通Java字节码(替换为Object类,移除所有的参数类型).  
? extends T & ? super T 区别:前者规定了保存的类型的上界,而后者规定了下界.  

- [内部类](https://github.com/MelloChan/java-interview/blob/master/java-exam/src/base/inner)  

内部类:顾名思义,在一个类的内部中定义类.用于将一些逻辑相关的类组织在一起.内部类的最大特性在于能与外部类进行通信了解(即能访问外部类的方法与字段,本质上是提供了外部类的this引用).    

[普通内部类](https://github.com/MelloChan/java-interview/blob/master/java-exam/src/base/inner/Outside.java):内部类的普遍用法.在类中定义类;  

[静态内部(嵌套)类](https://github.com/MelloChan/java-interview/blob/master/java-exam/src/base/inner/StaticOutside.java):使用static修饰的内部类,与内部类的差异在于无法访问外部类的方法/字段.这点由static的定义就可知晓.另外只有嵌套类能拥有static修饰的成员(参考static的定义);      

局部内部类 & 匿名内部类:在一个方法或任意的作用域中定义内部类.      

关于内部类与外部类通信的方式,通过查阅编译后的字节码即可了解.  
![原理](https://raw.githubusercontent.com/MelloChan/java-interview/master/java-exam/src/base/inner/out.jpg)  
![原理](https://raw.githubusercontent.com/MelloChan/java-interview/master/java-exam/src/base/inner/in.jpg)    
如上所见,其实就是提供了一个外部类的this引用,而私有字段(方法)的访问则是在外部类创建了该字段的静态访问方法.   
至于为什么需要内部类? 答:有效实现"多重继承".  
当然内部类的知识点&特性还有很多,详情查阅Thinking in Java.    

- 集合类  
![集合类](https://raw.githubusercontent.com/MelloChan/java-interview/master/image/%E9%9B%86%E5%90%88%E7%B1%BB.png)

List:实现该接口的常用类为ArrayList & LinkedList.    

[ArrayList](https://github.com/MelloChan/java-interview/blob/master/content/ArrayList.md):底层结构为(可动态改变大小)数组,默认容量10,扩充时增大到原先的1.5倍.适合频繁取值以及尾部插入或删除的场景;  

[LinkedList](https://github.com/MelloChan/java-interview/blob/master/content/LinkedList.md):底层结构为双向链表(同时也实现了队列接口),增删快,但查询或修改值时较慢(需要遍历).因此更适合频繁从中间插入或删除的场景.  
  
CopyOnWriteArrayList:写时复制的数组列表,适用于读多写少的场景.因为在添加新元素时不会直接往当前容器添加,而是先将容器进行复制然后往新容器加入元素,完毕后在将引用指向新容器.
因此在并发读取时并不会加锁.  

Set:常用类为HashSet & TreeSet & LinkedHashSet.  

通过查阅源码,HashSet本质上就是个值总为静态常量Object的HashMap,同样TreeSet是按照自然排序的TreeMap,LinkedHashSet则是按照元素添加顺序的HashSet.

Map:常用类为HashMap & LinkedHashMap & HashTable & ConcurrentHashMap.   

[HashMap](https://github.com/MelloChan/java-interview/blob/master/content/HashMap.md):底层结构为数组+链表(JDK8,链表长度为8则转为红黑树),其提供键值对(可为null,此时key的hashcode为0)映射,但不保证顺序(包括在扩容时可能的改变),默认大小为16(总是为2的幂次方,目的是减少哈希冲突),负载因子0.75
(即实质上容量为0.75乘以16),扩容时为原始容量的两倍.    
get方法:用来获取值,该方法通过对key的hashcode进行哈希获取索引,找到数组中的对应链表,当链表长度大于2时,遍历链表,通过equals方法来比key获取值;  
put方法:压入键值对,首先会对key的hashcode进行哈希获得索引,初次使用时才对数组进行内存分配,之后新建一个链表结点(包含hash值,key,value,next)存放指定数组位置.当发生哈希冲突时
(即key的hashcode哈希后得到相同的索引),则通过equals比对key,若key相同则覆盖原始值,否则新建结点,插入原始链表的尾部(JDK7是插入头部,作者认为新插入的键值总是会更频繁的被访问,避免尾部遍历,而JDK8改为尾插),当链表长度等于8时将链表转为红黑树(遍历效率问题,O(N)到O(logN)).  
resize方法:当数组容量超过负载因子*数组大小时,将进行数组扩容,扩容的大小为原始的2倍.在多线程下,扩容可能引发环形链表(死循环).

LinkedHashMap:HashMap的子类,保存了元素的插入顺序(或最近最少使用的顺序),迭代访问元素时比HashMap,因为内部使用了链表维护次序.其余和HashMap差别不大.

HashTable:线程安全的哈希表,不允许null键值对,使用方法级别的同步锁(synchronized),任意时刻只有一条线程能读写HashTable,因此性能很差;  

ConcurrentHashMap:线程安全的哈希表,采用分段锁,只针对写进行加锁,因此在性能上高于HashTable.增添了一个新概念Segment,每个Segment都包含一个原先的桶数组,通俗讲就是一个二级哈希表(JDK7).

Queue:常用的有LinkedList(即实现了链表也实现队列接口)和PriorityQueue(优先队列).  

- [ArrayList](https://github.com/MelloChan/java-interview/blob/master/content/ArrayList.md) & [LinkedList](https://github.com/MelloChan/java-interview/blob/master/content/LinkedList.md) 详解
- [HashMap](https://github.com/MelloChan/java-interview/blob/master/content/HashMap.md) ConcurrentHashMap(https://github.com/MelloChan/java-interview/blob/master/content/ConcurrentHashMap.md) 详解

- 异常相关     

Throwable:用来表示任何可以作为异常被抛出的类.其子类有两种:①Error;②Exception.  
Error:用来表示编译时和系统错误(一般不需要开发者关心此类错误);  
Exception:可以被抛出的基本类型,在Java类库、用户方法以及运行时故障中都可能抛出Exception异常.因此也是开发者最需要关心的.  

异常类型分为检查异常(CheckedException)与非检查异常(UncheckedException).  
前者代表开发者不能直接控制的因素(数据库问题、用户输入问题、网络异常以及文件丢失等,相应的关键字有try catch throw(方法内部) throws(方法签名) finally),例如SQLException、IOException、ClassNotFoundException等;  
后者异常属于错误(编程错误,开发者的锅),会被自动捕获(运行时无法恢复的异常),常见的有Error与RuntimeException以及子类,如OutOfMemoryError、
NullPointerException、IndexOutOfBoundsException、IllegalArgumentException等;  

- [代理机制](https://github.com/MelloChan/java-interview/blob/master/java-exam/src/base/proxy)   

[静态代理](https://github.com/MelloChan/java-interview/blob/master/java-exam/src/base/proxy/StaticProxy.java):自行创建代理类.实现起来很简单,局限性在于当业务剧增时需要实现的代理类就明显繁多了.

[动态代理](https://github.com/MelloChan/java-interview/blob/master/java-exam/src/base/proxy/DynamicProxy.java):
需要有统一的代理接口(内部生成代理类时需要),生成的代理类继承了Proxy,换句话说生成的代理类 -> $ProxyXXX extends Proxy implements XXInterface(即与被代理类是兄弟关系,都实现了接口).还有一个该接口的实现类,之后在有一个实现InvocationHandler接口的处理类来实现前后逻辑的增添.
动态代理的局限性在于只能针对接口动态生成代理类的,无法对类进行动态代理,因为动态生成的代理类都有共同的父类Proxy,而Java又是单继承的,无法再次继承被代理类.不过CGLIB弥补了这点.  
参考:https://www.ibm.com/developerworks/cn/java/j-lo-proxy1/index.html

扩展->CGLIB(字节码增强):动态代理需要被代理类实现一个统一接口(仅仅只支持接口的局限性),而CGLIB更像是产生一个被代理类的子类(即父子关系,当然若被代理类是final的就无法使用CGLIB了),这弥补了动态代理的不足.
CGLIB需要先引入cglib-nodep(或cglib + asm),创建一个类实现MethodInterceptor接口的intercept方法,传入需要被代理的方法.如下,比起动态代理调用更为简洁.
```
public class CglibDemo {
    public static void main(String[] args) {
        Service target=new Service();
        Service proxy= (Service) new CglibProxyFactory(target).getProxyIntance();
        proxy.doSomething();
    }
}
class Service{
    public void doSomething(){
        System.out.println("------ doSomething -------");
    }
}
class CglibProxyFactory implements MethodInterceptor{

    private Object target;

    public CglibProxyFactory(Object target){
        this.target=target;
    }

    public Object getProxyIntance(){
        // 工具类
        Enhancer en=new Enhancer();
        // 设置父类
        en.setSuperclass(target.getClass());
        // 设置回调方法
        en.setCallback(this);
        // 创建子类 代理对象
        return en.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("-----start------");
        Object result=method.invoke(target,objects);
        System.out.println("------end--------");
        return result;
    }
}
```

- [JDBC](https://github.com/MelloChan/java-interview/blob/master/java-exam/src/base/jdbc)  

Java数据库连接,是Java与数据库交互的方式,提供了用于执行SQL的Java API,同时统一了对多种关系数据库的访问,屏蔽了不同数据库间的访问差异,使开发者能更加专注开发,而无需为访问不同数据库而撰写另一个程序.    

方式:注册驱动类(Class.forName(“com.mysql.jdbc.Driver”);)->建立DB连接(DriverManager.getConnection("url"),username,password)->使用相关的CRUD API(PrepareStatement、execute等)->获取结果集(ResultSet...)......  

- BIO NIO AIO  

BIO:同步阻塞IO......;   

NIO:同步非阻塞IO......;  

AIO:异步IO......;  

- 创建类的方法  

①通过new关键字;②[使用clone方法](https://github.com/MelloChan/java-interview/blob/master/java-exam/src/base/create/CloneClass.java);③[使用反射机制](https://github.com/MelloChan/java-interview/blob/master/java-exam/src/base/reflect);④反序列化

- final finally finalize  

final:修饰字段(不可变)、方法(不可被重写)、类(不可被继承);  
finally:搭配try使用,finally总是会被执行,因此常用来做关闭资源(JDK7后推崇try-with-resource方式)、lock解锁之类的操作;  
finalize:一个历史遗留的方法,不被推荐使用.  

- [反射](https://github.com/MelloChan/java-interview/blob/master/content/reflect.md)  

动态获取某个类的字段与方法以及动态调用对象的字段与方法(包括私有).常见应用如动态代理、工厂模式之类.另外不明白反射机制就很难理解Spring(ioc & aop)的运行机制.

- 序列化与反序列化

- Java7新特性  

①switch语句支持字符串  
```
public void doSomething(String type){
        switch(type){
            case "xx":break;
            case "yy":break;
            default:break;
        }
        System.out.println("------ doSomething -------");
    }
}
```  
②泛型信息的类型自动化推断  
```
List<String> list = new ArrayList<>();
```
③新增Objects工具类  
④新增线程安全的ThreadLocalRandom类  
⑤可以使用 | 来进行多异常捕获  
```
try{
 
}catch(IndexOutOfBoundsException|NumberFormatException|ArithmeticException e)){

}catch(Excetion ex){

}
```
⑥try-with-resource 直接使用try()进行流关闭,比起finally更简便  
⑦增强的throw异常抛出,在catch中抛出异常,方法签名throws将更具体    

- Java8新特性
- Java9新特性