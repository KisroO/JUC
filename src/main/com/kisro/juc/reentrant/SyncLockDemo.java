package com.kisro.juc.reentrant;

/**
 * 可重入锁synchronized实现
 *     可重入锁也叫递归锁
 */
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
