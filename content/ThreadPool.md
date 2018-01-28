### Java线程池知识点

#### 作用  

①重用线程,减少线程创建与销毁带来的性能开销;  
②有效控制线程的并发数,提高资源利用率,避免过多的线程资源竞争与堵塞;  
③简单高效的管理多线程.

#### Executor  
  
Java线程池是基于Executor框架实现的,其主要包括接口与类:Executor、Executors、ExecutorService、ThreadPoolExecutor、Callable和Future以及FutureTask等.  
Executor:所有线程池的接口,只有一个方法.
```
public interface Executor {
    
    void execute(Runnable command);
}
```
ExecutorService:

#### 工作过程

#### 创建 & 使用

#### 原理