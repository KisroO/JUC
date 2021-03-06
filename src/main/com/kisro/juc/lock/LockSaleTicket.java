package com.kisro.juc.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用ReentrantLock可重入锁来实现锁定票数
 */
class Ticket2 {
    private final ReentrantLock lock = new ReentrantLock();
    private int nums = 30;
    void sale(){
        //上锁
        lock.lock();
        try {
            if(nums>0){
                nums--;
                System.out.println(Thread.currentThread().getName()+"卖出票；剩余"+nums);
            }
        }finally{
            //解锁
            lock.unlock();
        }
    }
}
public class LockSaleTicket {
    public static void main(String[] args) {
        Ticket2 ticket2 = new Ticket2();
        new Thread(()-> {
            for (int i = 0; i < 30; i++) {
                ticket2.sale();
            }
        },"aa").start();
        new Thread(()-> {
            for (int i = 0; i <30; i++) {
                ticket2.sale();
            }
        },"bb").start();
    }
}
