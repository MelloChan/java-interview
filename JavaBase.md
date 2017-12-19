# Java基础
### Java基础
- 四大基本特性  
封装/继承/多态/抽象  
封装: 使用不同的访问修饰符,让类的属性与方法只让可信的类或者对象实例操作,
对不可信的进行封装隐藏.即属性封装与方法封装.  
继承: 代码复用分为继承(is-a)与组合(has-a,具有更高的灵活性).对有着高度共同特性的的多列事物进行在抽象成一个类
.这个类既是父类,子类通过extends关键字继承父类抽象的共性.  
多态: 编译期调用/运行时动态确定调用的代码.方法的重载与重写(覆盖)体现了这两点.  
抽象: 将现实世界的某一类事物进行属性与方法提取,然后用程序代码实现,既是类或接口.
抽象包括两方面:①数据抽象,即对象属性的封装 ②过程抽象,即对象的行为特征.   
- 重载与重写的区别  
重载:重载的方法具有相同的方法名,其区别在于参数列表的不同,访问修饰符与返回值可以不同,发送在编译期.       
重写:运行时确定调用的代码.发生在父子类中,方法名与参数列表必须相同,返回值可以相同或者协变(返回类型小于等于父类的返回类型).抛出的异常小于等于父类,访问修饰符必须大于等于父类.
被private修饰的方法不会被重写.  
- 访问控制符
private default protected public 访问权限依次递增.  
private:私有的,只允许自身访问;  
default:默认包访问修饰符,不需要关键字.只允许自身以及同包的类访问;  
protected:受保护的.只允许自身以及其子类访问;  
public:公有的.允许所有的类对其访问.  
- 抽象类与接口
语法层次:抽象(abstract)允许普通与抽象方法存在,属性没有规定;接口(interface)方法默认为public abstract,字段默认为private static final,
在JDK1.8中允许有default关键字修饰的普通方法;
设计层次:抽象类是对类抽象,而接口则是行为抽象.抽象类是对客观世界拥有共性特征的事物进行整体抽象,是自底向上的,而接口则是局部行为抽象.
是自顶向下的.因此抽象类表现的是一种is-a关心,接口表现的则是like-a.
他们都不可被实例化.

- [类初始化顺序](https://github.com/MelloChan/java-interview/blob/master/java-exam/src/base/InitDemo.java)
首先是静态成员,其次是普通成员,最后则是构造方法;
父子类中,首先初始化父类静态属性/块,然后是子类静态属性/块,其次是父类普通成员/块,构造方法,然后才是子类的普通成员/块,构造方法.

- hashCode & equals
hashCode相等,值不一定相等,值相等则hashCode一定相等.因此hashCode方法一定要配合equals方法重写.

- [== & equals](https://github.com/MelloChan/java-interview/blob/master/java-exam/src/base/Compare.java)
对于基本类型的值比较使用 == 即可,但基于引用类型(诸如通过new String())之类的对象实例进行比较,则需要使用
equals方法,单纯使用 == 将会对引用的地址进行比较,而不是值.

- static

- this
- 基本类型 & 包装类
- String
- 泛型
- 内部类
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
- Java8