## java并发编程
> juc  即java.util.current

### Lock接口

1. ReentrantLock实现类
> Lock和synchronized区别：采用synchronized不需要用户手动释放锁，而Lock必须要用户释放锁，否则可能会造成死锁现象
> Lock不是Java内置，而synchronized是Java语言的关键字。

[![lock.png](https://z3.ax1x.com/2021/08/02/fS7pa8.png)](https://imgtu.com/i/fS7pa8) 

```java
public class Ticket{
    private final ReentrantLock lock = new ReentrantLock();
    //...
    public void sale(){
        lock.lock();//上锁
        try{
            //method body
        }finally{
        lock.unlock();//解锁
        }
    }
}
```

### 公平锁和非公平锁
[![fSje1S.png](https://z3.ax1x.com/2021/08/02/fSje1S.png)](https://imgtu.com/i/fSje1S)
> 创建ReentrantLock实例时，可以传入boolean类型参数，来创建公平锁和非公平锁
[![fSx5Sf.png](https://z3.ax1x.com/2021/08/02/fSx5Sf.png)](https://imgtu.com/i/fSx5Sf)
```java
//true则为公平锁，false为非公平锁
private final ReentrantLock lock = new ReentrantLock(false);
```
非公平锁：容易造成线程饿死，但效率高
[![fSxTOg.png](https://z3.ax1x.com/2021/08/02/fSxTOg.png)](https://imgtu.com/i/fSxTOg)
公平锁：效率相对较低
[![fSxo6S.png](https://z3.ax1x.com/2021/08/02/fSxo6S.png)](https://imgtu.com/i/fSxo6S)

### 可重入锁
> 可重入锁也叫递归锁

1.synchronized实现
```java
public class SyncLockDemo {
    public static void main(String[] args) {
        Object o = new Object();
        new Thread(()-> {
            synchronized (o){
                System.out.println(Thread.currentThread().getName()+"外层");
                synchronized (o){
                    System.out.println(Thread.currentThread().getName()+"中层");
                    synchronized (o){
                        System.out.println(Thread.currentThread().getName()+"内层");
                    }
                }
            }
        },"t1").start();
    }
}

//也类似于
public class SyncLockDemo{
    //此方法会抛出异常
    public synchronized void add(){
        add();
    } 
    public static void main(String[] args){
      new SyncLockDemo().add();
    }
}
```
2.ReentrantLock实现
```java
public class ReentrantLockDemo {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        new Thread(()-> {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName()+"外层");
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName()+"内层");
                }finally {
                    lock.unlock();
                }
            }finally {
                lock.unlock();
            }
        },"t1").start();
    }
}
```