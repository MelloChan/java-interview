### Java线程池知识点

#### 作用  

①重用线程,减少线程创建与销毁带来的性能开销;  
②有效控制线程的并发数,提高资源利用率,避免过多的线程资源竞争与堵塞;  
③简单高效的管理多线程.

#### demo
```
public class ThreadPoolDemo {
    // 固定大小的线程池
    private static ExecutorService service= Executors.newFixedThreadPool(10);
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            service.execute(new Task());
        }
    }
    static class Task implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }
}
```

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
// 提供指定线程数量的线程池 核心数与最大线程数都是 nThreads 因此总是保持着核心线程数 线程不会释放 使用无界队列 上限整型最大值
public static ExecutorService newFixedThreadPool(int nThreads) {
        return new ThreadPoolExecutor(nThreads, nThreads,
                                      0L, TimeUnit.MILLISECONDS,
                                      new LinkedBlockingQueue<Runnable>());
    }
 
// 提供单一线程的线程池 使用无界队列 因为是单一线程 因此也保证了任务执行的有序
public static ExecutorService newSingleThreadExecutor() {
        return new FinalizableDelegatedExecutorService
            (new ThreadPoolExecutor(1, 1,
                                    0L, TimeUnit.MILLISECONDS,
                                    new LinkedBlockingQueue<Runnable>()));
    }    
    
// 提供一个可缓存线程池  核心线程数0(这也意味着线程存活一定时间就会被回收 不会常驻)  最大线程数为整型最大值 线程存活时间60s  采用同步队列(任务会被立即执行) 
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
如上各种线程池所示,其实就是创建了一个ThreadPoolExecutor实例,我们可以使用Executors这个工厂类来快速获取一个线程池实例.不过根据阿里巴巴编程规约,更推荐
通过ThteadPoolExecutor的方式来创建线程池,原因是能更加明确线程池的运行规则.  
```
// 阿里巴巴编程规约
说明：Executors各个方法的弊端：
1）newFixedThreadPool和newSingleThreadExecutor:
  主要问题是堆积的请求处理队列可能会耗费非常大的内存，甚至OOM。
2）newCachedThreadPool和newScheduledThreadPool:
  主要问题是线程数最大数是Integer.MAX_VALUE，可能会创建数量非常多的线程，甚至OOM。
```

#### 线程池参数解析

#### 工作过程

#### 创建 & 使用

#### 原理