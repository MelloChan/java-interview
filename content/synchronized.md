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
②对于类级方法,锁是当前类的Class对象.    
③对于synchronized块,锁是括号内配置的对象.
