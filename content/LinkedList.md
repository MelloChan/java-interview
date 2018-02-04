### LinkedList 底层分析  

LinkedList一个双向链表,实现了List以及Deque(因此也是个队列)等接口.  
查看源码,内部字段只有三个:容量、头结点first以及尾节点last.节点Node源于一个私有的静态内部类.  
  
```

 transient int size = 0;
 
 transient Node<E> first;
 
 transient Node<E> last;
 
// 省略部分源码 
private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
```
主要关注下其插入方法与查找方法:  
```
 public boolean add(E e) {
        linkLast(e);
        return true;
    }
 
 // 仅仅是移动指针罢了 与数组拷贝相比效率提升很多 
 void linkLast(E e) {
       final Node<E> l = last;
       final Node<E> newNode = new Node<>(l, e, null);
       last = newNode;
       if (l == null)
           first = newNode;
       else
           l.next = newNode;
       size++;
       modCount++;
  }   
```