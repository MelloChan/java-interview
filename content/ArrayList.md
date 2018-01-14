### ArrayList 底层分析

ArrayList是一个可动态扩充大小的数组列表,实现了List<E>, RandomAccess等接口.可插入空数据,同时支持随机访问.
```$xslt
  // 两个主要参数 一个不会被序列化的对象数组以及一个size变量记录元素个数
  transient Object[] elementData; 
  private int size;
  // 省略部分源码 
  // 尾部插入
 public boolean add(E e) {
        // 校验容量
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        elementData[size++] = e;
        return true;
    }
 // 指定位置插入 应该尽量少用 
 public void add(int index, E element) {
         rangeCheckForAdd(index);
 
         ensureCapacityInternal(size + 1);  // Increments modCount!!
         System.arraycopy(elementData, index, elementData, index + 1,
                          size - index);
         elementData[index] = element;
         size++;
     }   
```
大小校验以及进行初始化(默认大小10)与扩容的方法:  
```$xslt
 private void ensureCapacityInternal(int minCapacity) {
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            // DEFAULT_CAPACITY = 10 
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }

        ensureExplicitCapacity(minCapacity);
    }

 private void ensureExplicitCapacity(int minCapacity) {
        modCount++;
        // overflow-conscious code
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }
 private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        // 每次扩容都为原先的1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        // minCapacity is usually close to size, so this is a win:
        // 调用Arrays工具类的复制方法 
        elementData = Arrays.copyOf(elementData, newCapacity);
    }
```
总结:ArrayList底层为默认容量为10的数组,每次扩容其实就是一次数组复制的过程,扩容为原先的1.5倍.由数组特性我们知道,其适合频繁取值以及尾部插入或删除的场景,在
需要频繁的中间插入或删除时则不适合使用(频繁的System.arraycopy).