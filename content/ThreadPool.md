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

ExecutorService:继承Executor接口,增加行为方法.  

Executors:使用工厂模式来提供线程池实例,返回的线程池都实现了ExecutorService接口.
```
// 提供指定线程数量的线程池 使用有界队列
public static ExecutorService newFixedThreadPool(int nThreads) {
        return new ThreadPoolExecutor(nThreads, nThreads,
                                      0L, TimeUnit.MILLISECONDS,
                                      new LinkedBlockingQueue<Runnable>());
    }
 
// 提供单一线程的线程池    
public static ExecutorService newSingleThreadExecutor() {
        return new FinalizableDelegatedExecutorService
            (new ThreadPoolExecutor(1, 1,
                                    0L, TimeUnit.MILLISECONDS,
                                    new LinkedBlockingQueue<Runnable>()));
    }    
    
// 提供一个可缓存线程池 初始容量0  最大线程数基本为整型最大值 采用同步队列 自动回收线程
public static ExecutorService newCachedThreadPool() {
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                                      60L, TimeUnit.SECONDS,
                                      new SynchronousQueue<Runnable>());
    }    
    
// 可设置核心线程数 最大线程数为整型最大值  支持定时以及周期任务
public ScheduledThreadPoolExecutor(int corePoolSize) {
        super(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,
              new DelayedWorkQueue());
    }    
```

ThreadPoolExecutor:  

#### 工作过程

#### 创建 & 使用

#### 原理