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

```
public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler)
```
corePoolSize:   
线程池的核心线程数,当提交一个任务时,线程池会判断当前线程是否小于核心线程池,是就创建一个新线程执行任务,否则任务被加入阻塞队列.       

maximumPoolSize:   
最大线程数,当队列满的时候,新提交的任务会检查当前线程数是否小于最大线程数,是就新建线程执行任务,否则执行拒绝策略.

keepAliveTime & unit:      
核心线程外的线程存活时间,unit为单位参数.

workQueue:    
用来保存等待被执行的任务(任务必须实现Runnable接口)的阻塞队列.JDK主要提供了如下队列实现:  
①ArrayBlockingQueue:基于数组结构的有界队列(先进先出);      
②LinkedBlockingQueue:基于链表的无界队列(先进先出);    
③PriorityBlockingQueue:具有优先级的无界队列;    
④SynchronousQueue:同步队列,不保存任务,任务插入与移除是同时的.   
    
threadFactory:  
创建线程的工厂类,默认使用Executors的静态内部类DefaultThreadFactory类:
```
static class DefaultThreadFactory implements ThreadFactory {
      
        DefaultThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                                  Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" +
                          poolNumber.getAndIncrement() +
                         "-thread-";
        }
    }
```  

handler:
用来执行拒绝策略的处理器,当队列满且无法创建新线程的时候会被执行,主要有以下策略:  
①ThreadPoolExecutor.AbortPolicy:默认策略,直接抛出异常;     
②ThreadPoolExecutor.CallerRunsPolicy:用调用者当前线程执行任务;       
③ThreadPoolExecutor.DiscardOldestPolicy:丢弃对头,执行当前任务;       
④ThreadPoolExecutor.DiscardPolicy:丢弃当前到来的任务;   
⑤自定义策略,需要实现RejectedExecutionHandler接口.  

#### 实现原理   

线程池除了ScheduledThreadPoolExecutor类,其他都是基于ThreadPoolExecutor类实现的.  
查看其源码,线程状态由相应字段决定:
```
    // 32位的整型 其中高3位表示线程状态 剩余29位表示线程数量
    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;
     
    /*    
     *   RUNNING:  Accept new tasks and process queued tasks
     *   SHUTDOWN: Don't accept new tasks, but process queued tasks
     *   STOP:     Don't accept new tasks, don't process queued tasks,
     *             and interrupt in-progress tasks
     *   TIDYING:  All tasks have terminated, workerCount is zero,
     *             the thread transitioning to state TIDYING
     *             will run the terminated() hook method
     *   TERMINATED: terminated() has completed
   */
    private static final int RUNNING    = -1 << COUNT_BITS;  // 高3位 111 运行状态 可以接收新任务 同时也会处理阻塞队列中的任务
    private static final int SHUTDOWN   =  0 << COUNT_BITS;  // 高3位 000  关闭状态 不能接收新任务 但会处理阻塞队列中的任务
    private static final int STOP       =  1 << COUNT_BITS;  //  001  停止状态 不接收新任务 不处理队列 直接中断运行中的线程任务
    private static final int TIDYING    =  2 << COUNT_BITS;  //  010  过渡状态 队列与线程池皆空情况下
    private static final int TERMINATED =  3 << COUNT_BITS;  //  011  结束状态 线程池为空
```

状态转换:   
```
     * RUNNING -> SHUTDOWN  
     *    On invocation of shutdown(), perhaps implicitly in finalize()
     * (RUNNING or SHUTDOWN) -> STOP
     *    On invocation of shutdownNow()
     * SHUTDOWN -> TIDYING
     *    When both queue and pool are empty
     * STOP -> TIDYING
     *    When pool is empty
     * TIDYING -> TERMINATED
     *    When the terminated() hook method has completed
```

任务提交,提供了两种方式,Executor接口的execute方法和ExecutorService接口的submit方法,
前者提交的任务必须实现Runnable接口同时不会返回值,无法确定任务是否执行成功;后者可以获取返回值,有多个重载实现:  
```
 // 该接口方法由ThreadPoolExecutor类实现
 public void execute(Runnable command) {
        if (command == null)
            throw new NullPointerException();
 
        int c = ctl.get();
        if (workerCountOf(c) < corePoolSize) {
            if (addWorker(command, true))
                return;
            c = ctl.get();
        }// 若当前线程数小于核心线程数 则调用addWorker方法新建线程执行任务
        if (isRunning(c) && workQueue.offer(command)) {
            int recheck = ctl.get();
            if (! isRunning(recheck) && remove(command))
                reject(command);
            else if (workerCountOf(recheck) == 0)
                addWorker(null, false);
        } // 若线程处于运行状态且成功将将任务加入队列 则重新获取原子整型值(状态+线程数) 若此时线程池不处于运行状态且成功将任务移出队列 则执行拒绝策略 
        else if (!addWorker(command, false))
            reject(command);
          // 尝试重新创建线程(线程数已经大于等于核心线程数了)执行任务 若当前线程数大于等于最大线程数则则执行拒绝策略  
    }/*
    总结: 1.线程数处于核心线程数范围,创建线程执行任务;
          2.线程数大于等于核心线程数,将任务加入队列;
          3.队列满了,线程数小于最大线程数,创建新线程执行任务;
          4.队列满了,线程数大于等于最大线程数,执行拒绝策略
    */
    
 // 该接口方法由AbstractExecutorService类实现    
 public <T> Future<T> submit(Callable<T> task) {
        if (task == null) throw new NullPointerException();
            RunnableFuture<T> ftask = newTaskFor(task);
            execute(ftask);
            return ftask;
        }
```

addWorker的实现,该方法用来新建线程执行任务,两个参数分别表示执行的任务以及当前线程数是否处于核心数量:  
```
private boolean addWorker(Runnable firstTask, boolean core) {
        retry:
        for (;;) {
            int c = ctl.get();
            int rs = runStateOf(c);

            // 当线程状态大于等于SHUTDOWN(关闭)时 直接返回false
            if (rs >= SHUTDOWN &&
                ! (rs == SHUTDOWN &&
                   firstTask == null &&
                   ! workQueue.isEmpty()))
                return false;
            
            //   通过core参数来判断需要创建的线程是否为核心线程 是则break死循环,创建新线程执行任务
            for (;;) {
                int wc = workerCountOf(c);
                if (wc >= CAPACITY ||
                    wc >= (core ? corePoolSize : maximumPoolSize))
                    return false;
                if (compareAndIncrementWorkerCount(c))
                    break retry;
                c = ctl.get();  // Re-read ctl
                if (runStateOf(c) != rs)
                    continue retry;
                // else CAS failed due to workerCount change; retry inner loop
            }
        }
        
        // 创建新线程的过程 基于Worker类,被final修饰同时它继承AQS实现了Runnable接口 
        // 由ReentrantLock类来保证工作线程被同步插入HashSet 
        boolean workerStarted = false;
        boolean workerAdded = false;
        Worker w = null;
        try {
            w = new Worker(firstTask);
            final Thread t = w.thread;
            if (t != null) {
                final ReentrantLock mainLock = this.mainLock;
                mainLock.lock();
                try {
                    // Recheck while holding lock.
                    // Back out on ThreadFactory failure or if
                    // shut down before lock acquired.
                    int rs = runStateOf(ctl.get());

                    if (rs < SHUTDOWN ||
                        (rs == SHUTDOWN && firstTask == null)) {
                        if (t.isAlive()) // precheck that t is startable
                            throw new IllegalThreadStateException();
                        workers.add(w);
                        int s = workers.size();
                        if (s > largestPoolSize)
                            largestPoolSize = s;
                        workerAdded = true;
                    }
                } finally {
                    mainLock.unlock();
                }
                if (workerAdded) {
                //  开启线程来执行任务
                    **t.start();**
                    workerStarted = true;
                }
            }
        } finally {
            if (! workerStarted)
                addWorkerFailed(w);
        }
        return workerStarted;
    }
    
// Worker类部分代码 从构造器中可以知道 thread字段由一个工厂方法赋值 
// 同时传入Worker实例 通过run方法线程启动时调用runWorker来执行任务
private final class Worker
        extends AbstractQueuedSynchronizer
        implements Runnable{
            final Thread thread;
            /** Initial task to run.  Possibly null. */
            Runnable firstTask;
    
            Worker(Runnable firstTask) {
                setState(-1); // inhibit interrupts until runWorker
                this.firstTask = firstTask;
                **this.thread = getThreadFactory().newThread(this);**
            }
  
            public void run() {
                **runWorker(this);**
            }
    }    
      
//  获取第一个任务 执行我们任务run方法(执行前后会加锁) 
//  之后继续通过getTask()获取阻塞队列中的任务
final void runWorker(Worker w) {
        Thread wt = Thread.currentThread();
        **Runnable task = w.firstTask**;
        w.firstTask = null;
        w.unlock(); // allow interrupts
        boolean completedAbruptly = true;
        try {
            while (task != null || (task = **getTask()**) != null) {
                w.lock();
                // If pool is stopping, ensure thread is interrupted;
                // if not, ensure thread is not interrupted.  This
                // requires a recheck in second case to deal with
                // shutdownNow race while clearing interrupt
                if ((runStateAtLeast(ctl.get(), STOP) ||
                     (Thread.interrupted() &&
                      runStateAtLeast(ctl.get(), STOP))) &&
                    !wt.isInterrupted())
                    wt.interrupt();
                try {
                    beforeExecute(wt, task);
                    Throwable thrown = null;
                    try {
                        task.run();
                    } catch (RuntimeException x) {
                        thrown = x; throw x;
                    } catch (Error x) {
                        thrown = x; throw x;
                    } catch (Throwable x) {
                        thrown = x; throw new Error(x);
                    } finally {
                        afterExecute(task, thrown);
                    }
                } finally {
                    task = null;
                    w.completedTasks++;
                    w.unlock();
                }
            }
            completedAbruptly = false;
        } finally {
            processWorkerExit(w, completedAbruptly);
        }
    }
  
 //  getTask通过自旋来获取任务 workQueue.take()获取任务,若没有任务则进入阻塞状态等待唤醒继续获取任务 (线程复用,因此线程池能一直执行用户提交的任务)
 //  workQueue.poll(keepAliveTime, TimeUnit.NANOSECONDS) 线程存活时间内队列还是没有任务 直接返回null
 private Runnable getTask() {
        boolean timedOut = false; // Did the last poll() time out?

        for (;;) {
            int c = ctl.get();
            int rs = runStateOf(c);

            // Check if queue empty only if necessary.
            if (rs >= SHUTDOWN && (rs >= STOP || workQueue.isEmpty())) {
                decrementWorkerCount();
                return null;
            }

            int wc = workerCountOf(c);

            // Are workers subject to culling?
            boolean timed = allowCoreThreadTimeOut || wc > corePoolSize;

            if ((wc > maximumPoolSize || (timed && timedOut))
                && (wc > 1 || workQueue.isEmpty())) {
                if (compareAndDecrementWorkerCount(c))
                    return null;
                continue;
            }

            try {
                **Runnable r = timed ?
                    workQueue.poll(keepAliveTime, TimeUnit.NANOSECONDS) :
                    workQueue.take();**
                if (r != null)
                    return r;
                timedOut = true;
            } catch (InterruptedException retry) {
                timedOut = false;
            }
        }
    }           
```

