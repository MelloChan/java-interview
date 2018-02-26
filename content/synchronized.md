### synchronized原理解析

#### Demo & JVM指令
```
 private final Object object = new Object();

 public void methodA() {
        synchronized (object) {
            System.out.println(Thread.currentThread().getName());
            System.out.println("-----START------");
            doSomething();
            System.out.println("-----END-------");
        }
    }
 
 // 注意标记6 39  45  对于synchronized块使用的是 monitorenter & monitorexit这两个指令来表示加锁与释放锁  
 public void methodA();
     Code:
        0: aload_0
        1: getfield      #3                  // Field object:Ljava/lang/Object;
        4: dup
        5: astore_1
        6: monitorenter
        7: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
       10: invokestatic  #5                  // Method java/lang/Thread.currentThread:()Ljava/lang/Thread;
       13: invokevirtual #6                  // Method java/lang/Thread.getName:()Ljava/lang/String;
       16: invokevirtual #7                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       19: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
       22: ldc           #8                  // String -----START------
       24: invokevirtual #7                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       27: invokestatic  #9                  // Method doSomething:()V
       30: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
       33: ldc           #10                 // String -----END-------
       35: invokevirtual #7                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       38: aload_1
       39: monitorexit
       40: goto          48
       43: astore_2
       44: aload_1
       45: monitorexit
       46: aload_2
       47: athrow
       48: return
     Exception table:
        from    to  target type
            7    40    43   any
           43    46    43   any   
```

#### 表现形式  

①对于普通方法,锁是当前实例对象.    
②对于类级(静态)方法,锁是当前类的Class对象.    
③对于synchronized块,锁是括号内配置的对象.  
更详细的说法也可分为 类锁(即静态方法上的锁)、对象锁(即对象实例)、代码块锁(synchronized代码块)以及同步方法锁(方法签名上添加synchronized).
  
#### 用法实例  
```
```
  
#### 锁优化  

针对同步关键字synchronized,JDK6为了减少锁获取与释放的性能消耗引入了偏向锁与轻量级锁,在JDK6之后锁一共有四种状态:无锁状态、偏向锁状态、轻量级锁状态和重量级锁状态,这些状态会随着线程竞争而升级(只能升级不能降级).这些状态存储在对象头 Mark Work中.    

偏向锁:同步代码仅仅被一个线程锁访问,此时使用高效的偏向锁,不需要同步;    

轻量级锁:当锁是为偏向锁时,被其他线程所访问,此时偏向锁就会升级为轻量级锁.线程通过自旋来尝试获取锁,此时并不会阻塞;    

重量级锁:当自旋达到一定次数时,轻量级锁就会升级为重量级锁.此时其他线程进入同步阻塞.    
  
  


