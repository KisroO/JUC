package com.kisro.juc.lock;

import java.util.concurrent.locks.ReentrantLock;
class Ticket3{
    //true则为公平锁，false为非公平锁
    private final ReentrantLock lock = new ReentrantLock(false);
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
public class FairLock {
    public static void main(String[] args) {
        Ticket3 ticket3 = new Ticket3();
        new Thread(()-> {
            for (int i = 0; i < 30; i++) {
                ticket3.sale();
            }
        },"aa").start();
        new Thread(()-> {
            for (int i = 0; i <30; i++) {
                ticket3.sale();
            }
        },"bb").start();
    }
}
