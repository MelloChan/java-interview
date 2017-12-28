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

- HashMap HashTable currentHashMap 联系与区别
- ArrayList & LinkedList 区别
- 异常相关
- 代理机制
- JDBC
- BIO NIO AIO
- 创建类的方法
- final finally finalize
- 序列化与反序列化
- Java7新特性
- Java8新特性
- Java9新特性