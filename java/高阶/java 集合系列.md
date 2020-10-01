# Java 集合系列
## Java Queue
### Queue 介绍
    此接口是 Java Collections Framework 的成员。

    Java Queue接口扩展了Collection接口。Collection接口 externsIterable接口。

    子接口：BlockingQueue, Deque, BlobkingDequeue

    一些最常用的Queue实现类是LinkedList，ArrayBlickingQueue, LinkedBlockingQueue，PriorityQueue, PriorityBlockingQueue。
### Queue 方法
    boolean  add(E e) //将指定的元素插入此队列（如果立即可行且不会违反容量限制），在成功时返回 true，如果当前没有可用的空间，则抛出 IllegalStateException。

    E   element() //获取，但是不移除此队列的头。

    boolean  offer(E e) //将指定的元素插入此队列（如果立即可行且不会
    违反容量限制），当使用有容量限制的队列时，此方法通常要优于 add(E)，后者可能无法插入元素，而只是抛出一个异常。
    
    E   peek() //获取但不移除此队列的头；如果此队列为空，则返回 null。

    E   poll() //获取并移除此队列的头，如果此队列为空，则返回null。

    E   remove() //获取并移除此队列的头。
### Queue 实现类
#### 