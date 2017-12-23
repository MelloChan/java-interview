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
定义:当多个线程访问某个类时,这个类始终都能表现出正确的行为,那么就称这个类时线程安全的.  
--Java并发编程实战(P13)  
①无状态的对象一定是线程安全的; ②不可变对象一定是线程安全的; ③加锁.

- synchronized & lock 
- volatile
- 死锁
- ThreadLocal
- sleep & wait 
- 线程通信
- 线程池
- [实现男女共浴问题](https://github.com/MelloChan/java-interview/blob/master/java-exam/src/thread/Bath.java)
- 并发包  
- 无锁机制