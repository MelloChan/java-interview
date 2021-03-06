### 多线程&并发

- 进程与线程的区别  

进程:具有一定独立功能的程序,是系统进行资源分配的基本单位;    

线程:又称轻量级进程,不拥有系统资源(除PC/寄存器/栈),本身在进程中,是CPU调度和分派的基本单位.多线程共享进程资源.    

区别:进程拥有独立的地址空间,一个进程至少有一个线程,而线程不能独立执行程序,必须依赖在应用程序中,由应用程序进行执行控制.因此多线程间是共享内存资源的,同时并发性也更高.
总的来说,多个进程对于操作系统来说是多个独立不同的应用程序,而多线程并不是,他依附在进程中.  

- 进程通信方式

1.管道:一般通过空文件实现,又分为普通管道(单工且进程间必须有亲缘关系)、流管道(半双工但进程间必须有亲缘关系)以及命名管道(半双工,进程间不需要有亲缘关系);      

2.消息队列:消息队列是由消息的链表,存放在内核中并有消息队列标识符标识.消息队列克服了信号传递信息少、管道只能承载无格式字节流以及缓冲区大小受限等缺点;    

3.套接字:进程通信机制的一种,不同的是其允许不同机器间的进程通信;    

4.共享内存:最快通信方式,操作系统通过建立一块共享内存,并将其映射到每个进程的地址空间上,进程就可以直接在这块共享内存进行读写;         

5.信号量:信号量是一个计数器,可以用来控制多个进程对共享资源的访问.它常作为一种锁机制,防止某进程正在访问共享资源时,其他进程也访问该资源.因此,主要作为进程间以及同一进程内不同线程之间的同步手段.      

6.信号:信号是一种比较复杂的通信方式,用于通知接收进程某个事件已经发生.

- [创建线程的方式](https://github.com/MelloChan/java-interview/blob/master/java-exam/src/thread/CreateThreadDemo.java)  

①继承Thread; ②实现Runnable(或Callable); ③线程池   
  
扩展:Callable + Future,前者通过线程池异步产生任务结果,后者负责获取结果.另外即使发生异常Callable也能返回异常结果,即Future能拿到异步执行后的任何任务结果.
Future.get()则会阻塞当前主线程直到Callable执行完成.
```
public class ThreadPoolDemo {

    private static ExecutorService service= Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException {
        Future<String>future=service.submit(new Task02());
        try {
            String result = future.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Task implements Callable<String>{

        @Override
        public String call(){
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Thread.currentThread().getName();
        }
    }
}
```
- 线程的状态转换  
![状态转换](https://raw.githubusercontent.com/MelloChan/java-interview/master/image/thread-state.png)    

Java线程进入阻塞状态主要有:  
①sleep方法或需要IO.针对sleep方法,此时若当前线程处于临界区拥有相关对象锁,进入阻塞状态并不会释放锁,仍然持有对锁的监控;  
②每个对象都拥有的wait方法,此时线程阻塞进入等待池,并释放锁,需要通过notify或notifyAll方法来唤醒(前者唤醒某个线程,后者唤醒所有等待中的线程来竞争锁);  
③临界区前的同步阻塞,synchronized或lock,此时只有获得锁的对象能进入临界区,其他在临界区外阻塞等待获取锁;  

- 线程安全  

定义:当多个线程访问某个类时,这个类始终都能表现出正确的行为,那么就称这个类是线程安全的.  
--Java并发编程实战(P13)  
①无状态对象一定是线程安全的; ②不可变对象一定是线程安全的; ③加锁.  

- 上下文切换   

多线程并不一定能提高程序执行速度,因为线程间的切换是需要时间的,因此在CPU密集型程序多线程将导致频繁的上下文切换导致程序运行变慢,
在IO密集型才更能发挥多线程的作用;
  
- [死锁](https://github.com/MelloChan/java-interview/blob/master/java-exam/src/thread/Deadlock.java)  

死锁:多线程无序的资源竞争导致程序陷入停滞死循环;  
死锁的四个必要条件:  
①互斥(进程在某一时间独占资源)  
②请求与保持(对已持有的资源保持占用,同时仍然请求被需要的但被阻塞的资源)  
③不可剥夺(对已持有的资源,进程若没有释放,系统无法强行剥夺)  
④环路等待(若干进程对资源的请求形成一个循环等待关系)  
如何预防死锁?  
①尽量一个线程获取一个锁; ②一个线程占用一个资源; ③保证锁会被释放(定时)  
其实就是不要满足四个必要条件.

- 同步 异步 阻塞 非阻塞   

通俗讲:    
同步与异步描述的是行为方式(即是否由我来主动获取结果),这里也分为了同步阻塞(遇到IO线程即被挂起知道IO结束才进入就绪状态),  
同步非阻塞(利用协程,另起线程监听IO状态),而异步显然总是非阻塞的(充分调动OS内核态,IO的状态不需要当前线程去监听,而是由OS来通知线程).  

阻塞和非阻塞,描述的是一种状态(IO过程中线程是否被挂起,即线程执行到IO时会被挂起知道IO结束,而非阻塞则可以继续执行后续代码,阻塞的区域根据实际情况有各种不同方式可选择).  
  
[参考理解.](https://www.zhihu.com/question/19732473)

- synchronized & Lock   

[synchronized](https://github.com/MelloChan/java-interview/blob/master/content/synchronized.md):JavaSE中JVM级别(monitorenter monitorexit字节码指令)的同步关键字,可作用在方法签名或块的可重入内置锁,线程进入同步代码块之前会自动获得锁,退出时自动释放,每次只有一个线程能持有锁;

[ReentrantLock](https://github.com/MelloChan/java-interview/blob/master/content/ReentrantLock.md):并发包下的一个接口,一般使用它的实现类ReentrantLock(可重入锁,基于CAS),提供了与synchronized相同的互斥性与内存可见性,但具有更高的灵活性,提供了可选的公平锁(按照线程请求前后顺序获取释放后的锁)、等待可中断以及锁绑定多个条件.    

ReentrantReadWriteLock:ReentrantLock作为标准的互斥锁,每次只允许一条线程访问临界区,虽然能避免各种线程安全问题(写/读,写/写,读/读),但对于读/读操作加锁就没那么必要了.只要每个线程
都能确保读取到最新的数据,并且在读取数据时不会有其他的线程修改数据,那么就不会发生问题.在这种情况使用读写锁能提升程序的性能.读写锁保证一个资源可用被多个读操作访问,或者被一个写操作访问,但两者不能同时进行.

- [volatile](https://github.com/MelloChan/java-interview/blob/master/content/volatile.md)

保证了修饰变量的内存可见性(变量的改变会通知所有线程工作内存中副本失效,转而重新载入读取主内存变量值),以及禁止指令重排序,但无法保证原子性.  
[详解](https://github.com/MelloChan/java-interview/blob/master/content/volatile.md)

- ThreadLocal 

线程的局部变量,[让每个线程都保存该变量的一份私有本地变量](https://github.com/MelloChan/java-interview/blob/master/java-exam/src/thread/ThreadLocalDemo.java).另外注意其并没有任何线程安全的保障,也就是说并不用来解决多线程共享变量的并发操作问题.ThreadLocal的目的是减少同一个线程内多个方法或组件间一些共享变量的传递的复杂度.  
    
- sleep & wait   

sleep不放弃对当前持有锁的监控(即同步块中休眠后进入阻塞状态但不释放锁),在休眠完毕后重新进入就绪状态等待运行,而wait方法会放弃锁(转为等待阻塞),然后等待调用notify或notifyAll方法来唤醒.

- 线程通信  

Object对象通有的方法,notify、notifyAll、wait等......

- [线程池](https://github.com/MelloChan/java-interview/blob/master/content/ThreadPool.md)

频繁创建线程显然是不合理的,因为对象的创建与销毁大都很耗时间(内存分配、相关资源获取以及JVM对象的跟踪与回收),一个例子就是JDBC中数据库连接的创建销毁都会有极大的性能开销.
而利用线程池,优点主要有:  
①重用线程,减少对象创建及销毁所带来的性能开销;  
②能有效的控制线程的最大并发数,提高系统资源利用率,同时避免过多的资源竞争与堵塞;  
③利用线程池,能更加简单高效的管理多线程.  
   
- [实现生产者-消费者模型](https://github.com/MelloChan/java-interview/blob/master/java-exam/src/thread/pc)
   
- [实现男女共浴问题](https://github.com/MelloChan/java-interview/blob/master/java-exam/src/thread/Bath.java)

- 并发包   
     
主要关注线程池、原子类、线程安全模型以及一些同步机制API.可归类为:  
①线程池相关 Executor框架;  
②并发集合(线程安全模型);  
③同步器.    

- CountDownLatch CyclicBarrier Semaphore    

在Effective Java第69条中建议,比起使用简陋的wait & notify(notifyAll)方法,更应当去使用并发包下高级的并发工具.  
 
CountDownLatch:闭锁或者说倒计数锁存器,允许一个或多个线程等待一个或者多个其他线程来做某些事.通俗点当某线程需要等待其他所有线程处理完相关事宜时才能运行,就可以使用.
```
// 联想操作系统课程中的P/V操作
public class CountDownLatchDemo {
    // 需要等待的线程数 
    static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Task()).start();
        new Thread(new Task()).start();

        System.out.println(Thread.currentThread().getName() + "------ 等待其他线程业务执行完毕 -------");
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+" ------- 其他线程业务已执行完毕 ---------");
        System.out.println(Thread.currentThread().getName()+" -------- 执行main主线程业务 --------");
    }/* output:
main------ 等待其他线程业务执行完毕 -------
Thread-1 ------- 某些业务 -------
Thread-0 ------- 某些业务 -------
Thread-1 ------- 业务完毕 -------
Thread-0 ------- 业务完毕 -------
main ------- 其他线程业务已执行完毕 ---------
main -------- 执行main主线程业务 --------
*/

    static class Task implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " ------- 某些业务 -------");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " ------- 业务完毕 -------");
            // 锁 -1
            countDownLatch.countDown();
        }
    }
}
```    

CyclicBarrier:同步屏障或者说回环屏障.让一组线程全部到达某种状态后在全部同时(相对意义上的同时)执行后续任务(在这之前这组线程会处于阻塞状态,另外某些场面CountDownLatch也可实现相同功能).另外当所有等待线程被释放该类可被重新使用.  
```
public class CyclicBarrierDemo {
    // 有两种构造器  只提供线程组数量的 和 线程组数量以及线程组调用await(即准备完毕)后触发的线程
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
        System.out.println("----- 恢复回环 -------");
    });

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(new Task()).start();
        }
    }/* output:
Thread-0------ 前置业务开始 -------
Thread-2------ 前置业务开始 -------
Thread-1------ 前置业务开始 -------
Thread-0------ 等待其他线程执行前置业务 ------
Thread-1------ 等待其他线程执行前置业务 ------
Thread-2------ 等待其他线程执行前置业务 ------
----- 恢复回环 -------
Thread-2------  前置业务结束,所有线程均到达屏障.后置业务开始 -------
Thread-0------  前置业务结束,所有线程均到达屏障.后置业务开始 -------
Thread-1------  前置业务结束,所有线程均到达屏障.后置业务开始 -------    
*/

    static class Task implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "------ 前置业务开始 -------");
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "------ 等待其他线程执行前置业务 ------");
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "------  前置业务结束,所有线程均到达屏障.后置业务开始 -------");
        }
    }
}

```      
CountDownLatch CyclicBarrier 区别:  
①后者是可重用的  
②前者一次countDown做减操作,后者await做加操作;     
③前者用于一个或多个线程等待另外一个或多个线程完成相关任务后才能执行,而后者是多个线程相互等待完成部分任务后执行await才能继续后续任务,任何一个线程完成之前(即未执行到await方法前),其他线程都必须处于阻塞状态,另外后者构造器可以传入一个触发线程(即所有线程都执行到await方法后触发).    

Semaphore:信号量.控制线程对资源的访问权限.一般是线程数量多于资源数量的情况.
```
// 联想操作系统课程中的信号量
public class SemaphoreDemo {
    /**
    资源数目 另外可实现公平锁 Semaphore(int permits, boolean fair)
     */
    private static Semaphore semaphore = new Semaphore(5);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Task()).start();
        }
    }

    static class Task implements Runnable {
        private static AtomicInteger count=new AtomicInteger();
        @Override
        public void run() {
            try {
                // 获取资源
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + ":"+count.getAndIncrement()+" 获取资源");
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + ":"+count.get()+" 释放资源");
                // 释放资源
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```  
     
- 锁优化  

针对synchronized关键字采取的锁优化策略.对象头里的mark work里存储对象的hashcode、分代年龄、偏向锁(默认是偏向锁)、锁标位等.  

偏向锁:不会使线程进入阻塞状态.一个线程访问临界区并获取锁时,会在对象头和栈帧中的锁记录里存储锁偏向的线程ID,以后该线程进入或退出临界区时不需要使用CAS来加锁和解锁.只需测试下mark work里是否存储着指向当前线程的偏向锁.
如果测试成功则表示线程已经获得了锁.如果失败则需要测试下mark work里偏向锁是否被设置为1,如果没有设置则需要竞争锁(通过CAS),否则通过CAS将对象头的偏向锁指向当前线程.    

轻量级锁:出现多线程访问临界区时偏向锁会被撤销.首先暂停拥有偏向锁的线程,然后检查该线程是否处于活动状态,不是则将对象头设置成无锁状态,是则解锁,mark work要么处于重新偏向其他线程要么恢复无锁要么标记对象不适合作为偏向锁.  

重量级锁:自旋多次失败,导致轻量级锁膨胀为重量级锁.此时线程将被同步,阻塞等待获取锁.

- 无锁机制  

CAS:即比较与替换(Compare and Swap),通过三个参数来保证并发,V 当前主内存中变量值,A 工作内存中的旧值,B 待更新值.只有在 V = = A返回true时,才会更新值.否则循环重来.  
Java并发包下大量使用了CAS技术,典型例子可以看AtomicInteger类源码:  
```
// 调用了Unsafe类方法
public final int incrementAndGet() {
        return unsafe.getAndAddInt(this, valueOffset, 1) + 1;
    }
    
public final int getAndAddInt(Object var1, long var2, int var4) {
            int var5;
            do {
               // 调用本地C++方法
                var5 = this.getIntVolatile(var1, var2);
            } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));
    
            return var5;
   }
```

缺点:ABA问题,即某线程更新值后为B之后又改回A(即主内存中V值为A),那么另一个读取了当前值后进行比较就会发现没问题而进行更新,然后实际上已经被更改过了.针对这种情况可以为变量值加上版本号以此区分.  