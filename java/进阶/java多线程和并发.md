
# java多线程创建
## java.lang.Thread 类
    
要想实现多线程，一种方法是继承Thread 类，实心run 方法。例
```
public class MyThread1 extend Thread{
    public void run(){
        System.out.println("hello");
    }
    
}
```
## java.lang.Runnable 接口

要想实现多线程，第二种方法是实现Runnable 接口，实现run方法 。实现Runnable的类必须依赖Thread才能启动 new Thread（new MyThread2()).start();
```
public class MyThread2 implements Runnable{
     public void run(){
        System.out.println("hello");
    }
}
```
    Runnable 接口是java的4大接口：Clonnable Comparable Serializable Runnable

# java 多线程启动
1. start 方法，会自动以新线程调用run方法（直接运行run方法是串行执行，不是多线程执行）
2. 同一个线程多次start 会报错，只能start 一次
3. 多个线程启动，先后顺序是随机的
4. 线程无需关闭，run方法执行结束后自动关闭
5. main函数（线程）可能早于新线程结束，整个程序并不终止。整个程序终止是所有的线程都终止

```

public class ThreadDemo1
{
	public static void main(String args[]) throws Exception
	{
		new TestThread1().start();
		while(true)
		{
			System.out.println("main thread is running");
			Thread.sleep(1000);
		}
	}
}

class TestThread1 extends Thread
{
	public void run() 
	{
		while(true)
		{
			System.out.println(" TestThread1 is running");
			try {
				Thread.sleep(1000); 
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
} 

```
```
public class ThreadDemo3
{
	public static void main(String args[])
	{
		//new TestThread3().start();
		
		TestThread3 tt= new TestThread3();
		Thread t= new Thread(tt);
		t.start();
		while(true)
		{
			System.out.println("main thread is running");
			try {
				Thread.sleep(1000); 
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
class TestThread3 implements Runnable //extends Thread
{
	public void run()
	{
		while(true)
		{
			System.out.println(Thread.currentThread().getName() +
			" is running");
			try {
				Thread.sleep(1000); 
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

```
## Thread vs Runnable

1. Thread占据了父类的名额，不如Runnable方便（Java 是单根继承，继承了Thread 意味着不能继承其他类）
2. Thread 类实现Runnable
3. Runnable启动时需要Thread类的支持
4. Runnable 更容易实现多线程中资源共享
5. 结论：建议实现Runnable接口来完成多线程

# java多线程信息共享
    通过共享变量达到信息共享，DK原生库暂不支持发送消息 (类似MPI并行库直接发送消息)
## volatile
### volatile 介绍
	先补充一下概念：Java 内存模型中的可见性、原子性和有序性。
#### 可见性：

	　　可见性是一种复杂的属性，因为可见性中的错误总是会违背我们的直觉。通常，我们无法确保执行读操作的线程能适时地看到其他线程写入的值，有时甚至是根本不可能的事情。为了确保多个线程之间对内存写入操作的可见性，必须使用同步机制。可见性，是指线程之间的可见性，一个线程修改的状态对另一个线程是可见的。也就是一个线程修改的结果。另一个线程马上就能看到。比如：用volatile修饰的变量，就会具有可见性。volatile修饰的变量不允许线程内部缓存和重排序，即直接修改内存。所以对其他线程是可见的。但是这里需要注意一个问题，volatile只能让被他修饰内容具有可见性，但不能保证它具有原子性。比如 volatile int a = 0；之后有一个操作 a++；这个变量a具有可见性，但是a++ 依然是一个非原子操作，也就是这个操作同样存在线程安全问题。

　　在 Java 中 volatile、synchronized 和 final 实现可见性。

#### 原子性：

	　　原子是世界上的最小单位，具有不可分割性。比如 a=0；（a非long和double类型） 这个操作是不可分割的，那么我们说这个操作时原子操作。再比如：a++； 这个操作实际是a = a + 1；是可分割的，所以他不是一个原子操作。非原子操作都会存在线程安全问题，需要我们使用同步技术（sychronized）来让它变成一个原子操作。一个操作是原子操作，那么我们称它具有原子性。java的concurrent包下提供了一些原子类，我们可以通过阅读API来了解这些原子类的用法。比如：AtomicInteger、AtomicLong、AtomicReference等。

　　在 Java 中 synchronized 和在 lock、unlock 中操作保证原子性。

#### 有序性：

	　　Java 语言提供了 volatile 和 synchronized 两个关键字来保证线程之间操作的有序性，volatile 是因为其本身包含“禁止指令重排序”的语义，synchronized 是由“一个变量在同一个时刻只允许一条线程对其进行 lock 操作”这条规则获得的，此规则决定了持有同一个对象锁的两个同步块只能串行执行。

```
public class ThreadDemo2
{
	public static void main(String args[]) throws Exception 
	{
		TestThread2 t = new TestThread2();
		t.start();
		Thread.sleep(2000);
		t.flag = false;
		System.out.println("main thread is exiting");
	}
}

class TestThread2 extends Thread
{
	//boolean flag = true;   //子线程不会停止
	volatile boolean flag = true;  //用volatile修饰的变量可以及时在各线程里面通知
	public void run() 
	{
		int i=0;
		while(flag)
		{
			i++;			
		}
		System.out.println("test thread3 is exiting");
	}	
} 

```
## synchronized
[synchronized 的使用](https://blog.csdn.net/zjy15203167987/article/details/82531772)

	synchronized是Java中的关键字，是一种同步锁。它修饰的对象有以下几种：
	1. 修饰一个代码块，被修饰的代码块称为同步语句块，其作用的范围是大括号{}括起来的代码，作用的对象是调用这个代码块的对象；
	2. 修饰一个方法，被修饰的方法称为同步方法，其作用的范围是整个方法，作用的对象是调用这个方法的对象；
	3. 修改一个静态的方法，其作用的范围是整个静态方法，作用的对象是这个类的所有对象；
	4. 修改一个类，其作用的范围是synchronized后面括号括起来的部分，作用主的对象是这个类的所有对象。

```

public class ThreadDemo3 {
	public static void main(String[] args) {
		TestThread3 t = new TestThread3();
		new Thread(t, "Thread-0").start();
		new Thread(t, "Thread-1").start();
		new Thread(t, "Thread-2").start();
		new Thread(t, "Thread-3").start();
	}
}

class TestThread3 implements Runnable {
	private volatile int tickets = 100; // 多个 线程在共享的
	String str = new String("");

	public void run() {
		while (true) {
			sale();
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			if (tickets <= 0) {
				break;
			}
		}

	}

	public synchronized void sale() { // 同步函数
		if (tickets > 0) {
			System.out.println(Thread.currentThread().getName() + " is saling ticket " + tickets--);
		}
	}
}

```
# 生产者消费者问题
```
package com.ctgu.D0730;

public class Product {
	int id;
	String name;
	public Product(int id,String name) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + "]";
	}
	
}
```

```
package com.ctgu.D0730;

public class Storage {
	private Product[] products = new Product[10];
	private int top = 10;
	public synchronized void push(Product product) {
		while(top == products.length) {
			try {
				System.out.println("Product wait");
				wait();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		products[top++] = product;
		System.out.println(Thread.currentThread().getName() + "生产了商品" + product);
		System.out.println("Producer notifyAll");
		notifyAll();
	}
	public synchronized Product pop() {
		while(top == 0) {
			try {
				System.out.println("consumer wait");
				wait();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		Product product = products[--top];
		products[top] = null;
		System.out.println(Thread.currentThread().getName() + "消费了商品" + product);
		System.out.println("Producer notifyAll");
		notifyAll();
		return product;
	}
}
```

```
package com.ctgu.D0730;

public class Consumer implements Runnable {
	private Storage storage;
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 0 ; i < 10 ;i ++) {
			storage.pop();
			try {
				Thread.sleep(100);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public Consumer(Storage storage) {
		// TODO Auto-generated constructor stub
		this.storage = storage;
	}
}

package com.ctgu.D0730;
```


```
package com.ctgu.D0730;

public class Producer implements Runnable{
	private Storage storage;
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 0 ; i < 10 ;i ++) {
			Product product = new Product(i + 1,"编号"+i);
			
			storage.push(product);
			try {
				Thread.sleep(100);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public Producer(Storage storage) {
		// TODO Auto-generated constructor stub
		this.storage = storage;
	}
}

```

```
// 生产者消费者问题

public class ProducerAndConsumer {
	public static void main(String[] args) throws InterruptedException {
		Storage storage = new Storage();
		Thread producer1 = new Thread(new Producer(storage));
		producer1.setName("producer1");
		Thread producer2 = new Thread(new Producer(storage));
		producer2.setName("producer2");
		Thread consumer1 = new Thread(new Consumer(storage));
		consumer1.setName("consumer1");
		Thread consumer2 = new Thread(new Consumer(storage));
		consumer1.setName("consumer2");
		producer1.start();
		producer2.start();
		Thread.sleep(500);
		consumer1.start();
		consumer2.start();
	}
}

```