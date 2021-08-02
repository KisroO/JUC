package com.kisro.juc.sync;


class Ticket {
    private int nums = 30;

    synchronized void sale() {
        if (nums > 0) {
            nums--;
            System.out.println(Thread.currentThread().getName() + "卖出票" + "：： 剩余：" + nums);
        }
    }
}

public class SyncTest {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                ticket.sale();
            }
        }, "aa").start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                ticket.sale();
            }
        }, "bb").start();
    }
}
