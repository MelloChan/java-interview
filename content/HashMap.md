### HashMap 底层分析(基于JDK8)

![数组+链表](https://raw.githubusercontent.com/MelloChan/java-interview/master/image/HashMap.png)  
如图,HashMap底层结构就是基于数组+链表实现的.其中的两个重要参数:  
DEFAULT_INITIAL_CAPACITY(默认容量) = 16;  
DEFAULT_LOAD_FACTOR(负载因子) = 0.75;  
当size>DEFAULT_INITIAL_CAPACITY*DEFAULT_LOAD_FACTOR,则自动扩容,调用resize()方法.   
 
 #### Node
 
 链表节点就是一个内部静态类,包含了hash值、key(冲突时equals来决定覆盖还是插入新节点)、value和下一个节点引用.  
 ```$xslt
 //部分源码
static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        V value;
        Node<K,V> next;

        Node(int hash, K key, V value, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
```

#### new

HashMap默认构造方法仅仅是将负载因子赋予默认值0.75,而节点数组只在初次put时才会分配内存,数组的大小总为2的幂次方,这是为了方便后面计算索引,直接 hash & size()-1比起取模运算要快.
```$xslt
 public HashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
    }
  
  public HashMap(int initialCapacity, float loadFactor) {
          if (initialCapacity < 0)
              throw new IllegalArgumentException("Illegal initial capacity: " +
                                                 initialCapacity);
          if (initialCapacity > MAXIMUM_CAPACITY)
              initialCapacity = MAXIMUM_CAPACITY;
          if (loadFactor <= 0 || Float.isNaN(loadFactor))
              throw new IllegalArgumentException("Illegal load factor: " +
                                                 loadFactor);
          this.loadFactor = loadFactor;
          this.threshold = tableSizeFor(initialCapacity); //总是返回2的幂次方大小
      }
      
 static final int tableSizeFor(int cap) {
              int n = cap - 1;
              n |= n >>> 1;
              n |= n >>> 2;
              n |= n >>> 4;
              n |= n >>> 8;
              n |= n >>> 16;
              return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
          }
```

#### hash

首先判断key是否为null,是直接返回0,否则获取key的哈希码,右移哈希码高16位->低16位(即此时高16位变为0),然后两者异或取得新的哈希整数(不变的高16位,低16位改变,目的是减少哈希冲突).
```$xslt
static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
```
之后将得到的 哈希整数 & 容量-1 取得索引
```$xslt
// i:索引 n:容量 hash:经过hash方法后得到的整型哈希
i = (n - 1) & hash
```

#### put  

#### get





