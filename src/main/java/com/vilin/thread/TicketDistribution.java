package com.vilin.thread;

import java.util.concurrent.TimeUnit;

public class TicketDistribution  extends Thread{

    private static final int MAX_NUM = 5000;
    private static int index = 1;

    @Override
    public void run() {
        synchronized (this) {
            while (index <= MAX_NUM) {
                try {
                    TimeUnit.MILLISECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " -- 叫到的号码：" + (index++));
            }
        }
    }

    public static void main(String[] args) {
        TicketDistribution t1 = new TicketDistribution();
        TicketDistribution t2 = new TicketDistribution();
        TicketDistribution t3 = new TicketDistribution();
        TicketDistribution t4 = new TicketDistribution();
        TicketDistribution t5 = new TicketDistribution();

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
