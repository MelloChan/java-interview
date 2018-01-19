### ConCurrentHashMap 底层分析

#### 作用
虽然JDK8以后HashMap链表死循环(JDK7时会发生的严重并发问题)已经解决,但HashMap本质上仍旧是非线程安全的,其并没有对类方法进行同步,因此多线程下仍然会发生诸如一个线程读另一个线程扩容的数据操作问题.  
因此并发环境应当考虑线程安全的哈希表,而ConcurrentHashMap自然是首选.另外要知道HashTable已经是废弃的类了,其线程安全是基于所有的方法都加上synchronized,性能极差.而ConcurrentHashMap可以视为一个二级哈希表(JDK7),线程安全基于分段锁,进行读取操作并不会阻塞.  
![二级哈希表](https://raw.githubusercontent.com/MelloChan/java-interview/master/image/ConCurrentHashMap.png)    
如上是JDK7的ConcurrentHashMap的底层结构,其默认大小64,segment数组默认16,因此二级哈希数组的大小为64/16=4.但JDK8重新设计了ConcurrentHashMap,抛弃了segment,底层仍然是数组+链表+红黑树,利用CAS算法解决并发问题.

#### initTable

#### hash

#### put

#### get

#### 

