### 多线程&并发
- 进程与线程的区别  

进程:具有一定独立功能的程序,是系统进行资源分配和调度的基本单位;    

线程:又称轻量级进程,不拥有系统资源(除PC/寄存器/栈),本身在进程中,是CPU调度和分派的基本单位.多线程共享进程资源.    

区别:进程拥有独立的地址空间,一个进程至少有一个线程,而线程不能独立执行程序,必须依赖在应用程序中,由应用程序进行执行控制.因此多线程间是共享内存资源的,同时并发性也更高.
总的来说,多个进程对于操作系统来说是多个独立不同的应用程序,而多线程并不是,他依附在进程中.  

- [创建线程的方式](https://github.com/MelloChan/java-interview/blob/master/java-exam/src/thread/CreateThreadDemo.java)  

①继承Thread; ②实现Runnable(或Callable); ③使用线程池.    

- 线程的状态转换  
![状态转换](https://raw.githubusercontent.com/MelloChan/java-interview/master/image/thread-state.png)  

- 线程安全  

定义:当多个线程访问某个类时,这个类始终都能表现出正确的行为,那么就称这个类是线程安全的.  
--Java并发编程实战(P13)  
①无状态对象一定是线程安全的; ②不可变对象一定是线程安全的; ③加锁.  

- 死锁  

死锁的四个必要条件:  
①互斥(进程在某一时间独占资源)  
②请求与保持(对已持有的资源保持占用,同时仍然请求被需要的但被阻塞的资源)  
③不可剥夺(对已持有的资源,进程若没有释放,系统无法强行剥夺)  
④环路等待(若干进程对资源的请求形成一个循环等待关系)  

- 同步 异步 阻塞 非阻塞   

这个问题口述没问题,想转成专业文字愣是不知道怎么写,看来还是理解不够......[参考理解.](https://www.zhihu.com/question/19732473)

- synchronized & Lock   

synchronized:JavaSE中JVM级别(monitorenter monitorexit字节码指令)的同步关键字,可作用在方法签名或块的可重入内置锁,线程进入同步代码块之前会自动获得锁,退出时自动释放,每次只有一个线程能持有锁;

Lock:并发包下的一个接口,一般使用它的实现类ReentrantLock(可重入锁),提供了与synchronized相同的互斥性与内存可见性,但具有更高的灵活性,提供了可选的公平锁(按照线程请求前后顺序获取释放后的锁)、等待可中断以及锁绑定多个条件.

- volatile
- ThreadLocal
- sleep & wait 
- 线程通信
- 线程池
- [实现男女共浴问题](https://github.com/MelloChan/java-interview/blob/master/java-exam/src/thread/Bath.java)
- CountDownLatch CyclicBarrier Semaphore  
- 并发包  
- 锁优化
- 无锁机制