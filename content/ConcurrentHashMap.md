### ConCurrentHashMap 底层分析

#### 作用
虽然JDK8以后HashMap链表死循环(JDK7时会发生的严重并发问题)已经解决,但HashMap本质上仍旧是非线程安全的,其并没有对类方法进行同步,因此多线程下仍然会发生诸如一个线程读另一个线程扩容的数据操作问题.  
因此并发环境应当考虑线程安全的哈希表,而ConcurrentHashMap自然是首选.另外要知道HashTable已经是废弃的类了,其线程安全是基于所有的方法都加上synchronized,性能极差.而ConcurrentHashMap可以视为一个二级哈希表(JDK7),线程安全基于分段锁,进行读取操作并不会阻塞.  
![二级哈希表](https://raw.githubusercontent.com/MelloChan/java-interview/master/image/ConCurrentHashMap.png)    
如上是JDK7的ConcurrentHashMap的底层结构,其默认大小64,segment数组默认16,因此二级哈希数组的大小为64/16=4.但JDK8重新设计了ConcurrentHashMap,抛弃了segment,底层仍然是数组+链表+红黑树,利用 CAS + synchronized解决并发问题.

#### Node  

对val和next字段增添了volatile关键字,解决并发可见性问题.
```
static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        volatile V val;
        volatile Node<K,V> next;

        Node(int hash, K key, V val, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.val = val;
            this.next = next;
        }
```

#### hash  

并发哈希表不允许键入null键值对,哈希操作放在了putVal方法  
```
int hash = spread(key.hashCode());
static final int spread(int h) {
        return (h ^ (h >>> 16)) & HASH_BITS;
    }
```

#### initTable   

同HashMap,数组的初始化放在第一次put操作时.  
```
if (tab == null || (n = tab.length) == 0)
                tab = initTable();
                
private final Node<K,V>[] initTable() {
        Node<K,V>[] tab; int sc;
        while ((tab = table) == null || tab.length == 0) {
             // sizeCtl 默认0 如果实例化时调用带参构造器则sizeCtl为2的幂次方 而大于等于零时线程会调用Unsafe的CAS方法修改sizeCtk为-1
             // 因此小于0证明其他线程在初始化中 当前线程让出CPU
            if ((sc = sizeCtl) < 0)
                Thread.yield(); // lost initialization race; just spin
            // 替换sizeCtl为-1 仅有一个线程能操作成功  参数分别为:当前对象 内存值 期待值 更新值, CAS就是根据 内存值是否等于期待值来替换更新    
            else if (U.compareAndSwapInt(this, SIZECTL, sc, -1)) {
                try {
                    // 替换成功的线程开始尝试初始化 扩容则为原先2倍 一定为2的幂次方
                    if ((tab = table) == null || tab.length == 0) {
                        int n = (sc > 0) ? sc : DEFAULT_CAPACITY;
                        @SuppressWarnings("unchecked")
                        Node<K,V>[] nt = (Node<K,V>[])new Node<?,?>[n];
                        table = tab = nt;
                        sc = n - (n >>> 2);
                    }
                } finally {
                    sizeCtl = sc;
                }
                break;
            }
        }
        return tab;
    }
```

#### put  

初始化完毕的哈希表开始计算索引然后插入数组中,不同的是JDK8采用 CAS+synchronized机制,而不是segment(分段锁,二级哈希)
```
    public V put(K key, V value) {
        return putVal(key, value, false);
    }

    final V putVal(K key, V value, boolean onlyIfAbsent) {
        if (key == null || value == null) throw new NullPointerException();
        int hash = spread(key.hashCode());
        int binCount = 0;
        for (Node<K,V>[] tab = table;;) {
            Node<K,V> f; int n, i, fh;
            if (tab == null || (n = tab.length) == 0)
                tab = initTable();
            else if ((f = tabAt(tab, i = (n - 1) & hash)) == null) {
                if (casTabAt(tab, i, null,
                             new Node<K,V>(hash, key, value, null)))
                    break;                   // no lock when adding to empty bin
            }
            else if ((fh = f.hash) == MOVED)
                tab = helpTransfer(tab, f);
            else {
                V oldVal = null;
                synchronized (f) {
                    if (tabAt(tab, i) == f) {
                        if (fh >= 0) {
                            binCount = 1;
                            for (Node<K,V> e = f;; ++binCount) {
                                K ek;
                                if (e.hash == hash &&
                                    ((ek = e.key) == key ||
                                     (ek != null && key.equals(ek)))) {
                                    oldVal = e.val;
                                    if (!onlyIfAbsent)
                                        e.val = value;
                                    break;
                                }
                                Node<K,V> pred = e;
                                if ((e = e.next) == null) {
                                    pred.next = new Node<K,V>(hash, key,
                                                              value, null);
                                    break;
                                }
                            }
                        }
                        else if (f instanceof TreeBin) {
                            Node<K,V> p;
                            binCount = 2;
                            if ((p = ((TreeBin<K,V>)f).putTreeVal(hash, key,
                                                           value)) != null) {
                                oldVal = p.val;
                                if (!onlyIfAbsent)
                                    p.val = value;
                            }
                        }
                    }
                }
                if (binCount != 0) {
                    if (binCount >= TREEIFY_THRESHOLD)
                        treeifyBin(tab, i);
                    if (oldVal != null)
                        return oldVal;
                    break;
                }
            }
        }
        addCount(1L, binCount);
        return null;
    }
```

#### get

#### 

