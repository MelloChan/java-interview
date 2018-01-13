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
取得索引的方式: 哈希整数 & 容量-1 
```$xslt
// i:索引 n:容量 hash:经过hash方法后得到的整型哈希
i = (n - 1) & hash
```

#### put  
首先查看put方法源码,方法putVal传入哈希后的key、key、value(另外两个参数暂时无视)  
```$xslt
 public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }
```
查看putValue方法源码,大致步骤如下:  
对key的hashCode()进行hash后计算数组索引index;  
如果当前数组为null则调用resize扩容;  
如果没碰撞则新建节点然后放入;  
冲突后如果节点已经存在(key值相等),则覆盖value;  
如果是树节点,则将节点挂载到树结构上;  
如果是链表,则尾插链表,如果链表长度大于等于8,则转换为红黑树;  
最后,如果数组大小已经超过实际可放入容量,则进行扩容(扩容为原先的2倍).  

```$xslt
final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        // Node<K,V>[] table 字段 若为null,则进行容量初始化
        if ((tab = table) == null || (n = tab.length) == 0)
            // 得到初始化后的容量
            n = (tab = resize()).length;
        //  容量-1 & hash值 取得索引,若索引位置为null,则新建节点放入数组    
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null);
        else { // 哈希冲突的情况
            Node<K,V> e; K k;
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))  // key相等的情况(值相等),直接覆盖,这里先赋值给e 
                e = p;
            else if (p instanceof TreeNode) // 节点属于树节点,自动挂载到红黑树上
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            else { // 这里进行尾插节点步骤,即冲突点key并不相等同时也不为树节点时
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {  // e赋值为p.next 冲突处的链表next指针为null,则尾插新节点
                        p.next = newNode(hash, key, value, null);
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st  链表长度大于等于8时转化为性能更高的红黑树 O（logn）复杂度
                            treeifyBin(tab, hash);
                        break;
                    }
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))  // key相等的情况(值相等),直接覆盖,这里先赋值给e 
                        break;
                     // 以上步骤皆为false 则遍历下个节点
                    p = e; 
                }
            }
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e); 
                // 有冲突的情况会返回冲突值(节点已存在)
                return oldValue;  
            }
        }
        ++modCount;
        // 如果数组大小 超过 负载因子*实际容量 则扩容
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
    }
```

#### get

#### resize



