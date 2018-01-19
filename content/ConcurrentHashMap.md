### ConCurrentHashMap 底层分析

#### 作用
虽然JDK8以后HashMap链表死循环(JDK7时会发生的严重并发问题)已经解决,但HashMap本质上仍旧是非线程安全的,其并没有对类方法进行同步,因此多线程下仍然会发送诸如一个线程读另一个线程扩容的数据操作问题.
因此并发环境应当考虑线程安全的哈希表,而ConcurrentHashMap自然是首选.另外要知道HashTable已经是废弃的类了,其线程安全是基于所有的方法都加上synchronized,性能极差.而ConcurrentHashMap可以视为一个二级哈希表,线程安全基于分段锁,进行读取操作并不会阻塞.
![二级哈希表]()