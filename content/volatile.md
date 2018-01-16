### volatile

#### 特性:  

①保证不同对象对修饰变量的内存可见性;  
②禁止指令重排序.  

#### JMM

首先了解下JMM(Java内存模型),JMM的目的是为了屏蔽不同硬件与OS对内存访问的差异,让Java程序在各种平台上有一致的内存访问.如下图,工作内存对应高速缓存,因为CPU指令执行的速度相对比访问内存的速度,两者不是一个数量级,因此为了加快访问速度而增设了临近CPU的高速缓存块.而JMM映射了这种模型.    
![内存模型](https://raw.githubusercontent.com/MelloChan/java-interview/master/image/JMM.png)  
工作内存是线程私有的,而主内存存储着各类全局变量,线程访问变量则需要从主内存中读取载入到自身工作内存.对变量进行相应操作后,才会重新存储写入到主内存,以此更新变量值.这些操作在单线程下不会出现问题,但多线程下就会出现并发问题了,因为虽然单一操作本身是原子性的,但组合起来就不一定了.  
例如:
```$xslt
// int i = 0;
  i++; 
```
i++的操作并不是原子性的,他首先从内存中读取载入到工作内存,随后进行+1操作,然后赋值在存储写入主内存.
```$xslt
// 假设有两个线程执行 i++
线程①:  从主存读取 i,  // i = 0  read->load
         对 i + 1,    // i = 1 假设此时时间片到时,线程切换 use assign
线程②:  从主存读取 i,  // 此时 线程①的操作还未写入主存,因此 i = 0
         对 i + 1,     
         将i写入主存    // store write i = 1 此时再次线程切换
线程①:  将i写入主存    // store write i = 1             
```
如上的执行流程,i的最终值将会是1(甚至可能是0),这就产生了缓存不一致的问题.  
而要解决缓存不一致问题,实质上就是如何处理JMM的原子性/可见性/有序性三个特性的问题,而volatile能够解决可见性与有序性.  

原子性:    
JMM的八种原子操作:lock(锁,将一个变量标识为一条线程独占的状态)/unlock(解锁)、read(从主内存中读取变量传输到工作内存)/load(read后执行,将传输得到的主内存变量值载入工作内存的变量副本中)、use(使用)、assign(赋值)、store(存储,作用于工作内存的变量副本,将工作内存的变量传送到主内存中)/write(写入,store后将从工作内存中得到的变量副本值写入主内存的变量中)    
```$xslt
// 假设i j 都为全局变量
i=1;  // 读取 
j=i;  // 先读取i在赋值给j
i++; // i=i+1; 读取i 进行+1 在赋值写入主存
j=j+1; // 同上
```
上面四条语句只有i=1符合原子性,其他都涉及到多种操作.  
如何保证原子性:可以使用同步关键字(synchronized,或Lock类),当然更好的方式是使用并发包下原子类来保证,其内部使用了CAS无锁机制来保证,性能更高.
  
[可见性](https://github.com/MelloChan/java-interview/blob/master/java-exam/src/thread/Visibility.java):  
可见性保证了当某线程修改了变量的值,对其他线程来说是可以立即得知的,其他线程的工作内存变量会立即失效,重新从主内存读取变量.但是,保证了可见性并一定就解决了并发问题,因为被修饰的变量执行非原子操作时,依然会导致缓存不一致问题.  
除了使用volatile保证可见性外,synchronized或Lock同样也可保证,因为在释放锁之前,变量的值会被刷新写入主内存.
     
[有序性](https://github.com/MelloChan/java-interview/blob/master/java-exam/src/thread/Order.java):  
只要执行结果不变,JMM是允许编译器和处理器对语句指令进行重排序的,例如:  
```$xslt
int i=1;  // A
int j=2;  // B
int k=i*j; // C
```
A->B->C或B->A->C,并不会影响语句最终的执行结果,因此可重排序,但C就不能在前,因为他是基于AB操作的.当然这是在单线程环境下,并发操作时就会产生问题.  
```$xslt
 private static boolean flag;
 private static int value;

 public static void main(String[] args) {
        new Thread(() -> {
            while (!flag) Thread.yield();
            System.out.println(value);
        }).start();
        value = 2;  // A
        flag = true;  // B
    }
```
如上,A->B或B->A都是可能的排序情况,解决方法是:
```$xslt
 private volatile static boolean flag;  // 加上volatile 禁止排序
```
volatile保证了线程对于一个volatile修饰的共享变量的写会立即刷新到主内存,而读取操作时,JMM先将工作内存的共享变量置为无效,然后线程去主内存读取共享变量.

#### volatile的保证

从以上我们得知了volatile对于可见性与禁止重排序的保证方式,但却无法保证复合操作下的原子性.  
例如:  
```$xslt
private static volatile int race=0;

    public static void inc(){
        race++; // 非原子操作
    }

    private static final int THREADS_COUNT=20;

    public static void main(String[] args) {
        for (int i = 0; i < THREADS_COUNT; i++) {
            new Thread(()->{
                for (int j = 0; j < 100; j++) {
                    inc();
                }
            }).start();
        }
        System.out.println(race);
    }
```
执行程序,你会发现race总是小于2000.这是因为当线程A读取了race的值,之后线程切换,其他线程对race进行++操作之后写回主内存.线程切换到线程A,它已经读取了race值,因此不会重新读取,
线程A对race++之后写回主内存,主内存的值被重新覆盖.因此race总是得到错误的值.
因此上述例子可以使用同步关键字或Lock,或者性能更佳,并发包下的原子类.  
例如:  
```$xslt
 private static volatile int race=0;
 private static AtomicInteger value=new AtomicInteger();

 public static void inc(){
        race++;
        value.getAndIncrement();
  }

 private static final int THREADS_COUNT=20;

 public static void main(String[] args) {
        for (int i = 0; i < THREADS_COUNT; i++) {
            new Thread(()->{
                for (int j = 0; j < 100; j++) {
                    inc();
                }
            }).start();
        }
        System.out.println(race);  // 总是小于2000 
        System.out.println(value);  // 2000 
  }
```

#### 底层实现


