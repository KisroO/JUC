## java并发编程
> juc  即java.util.current

### Lock接口

1. ReentrantLock实现类
> Lock和synchronized区别：采用synchronized不需要用户手动释放锁，而Lock必须要用户释放锁，否则可能会造成死锁现象
> Lock不是Java内置，而synchronized是Java语言的关键字。

![image](assets/Lock.png) 