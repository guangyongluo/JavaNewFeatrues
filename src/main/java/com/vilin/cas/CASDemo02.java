package com.vilin.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class CASDemo02 {

    private volatile static int m = 0;

    private static AtomicInteger atomicInteger = new AtomicInteger(100);

    private static AtomicStampedReference asr = new AtomicStampedReference(100, 1);

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            atomicInteger.compareAndSet(100, 110);
            System.out.println(atomicInteger.get());
        });
//        t1.start();

        Thread t2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicInteger.compareAndSet(110, 100);
            System.out.println(atomicInteger.get());
        });
//        t2.start();

        Thread t3 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicInteger.compareAndSet(100, 120);
            System.out.println(atomicInteger.get());
        });
//        t3.start();

        System.out.println("========================");

        Thread thread1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(asr.compareAndSet(100, 110, asr.getStamp(), asr.getStamp() + 1));
            System.out.println(asr.compareAndSet(110, 100, asr.getStamp(), asr.getStamp() + 1));
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            int stamp = asr.getStamp();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(asr.compareAndSet(100, 120, stamp, stamp + 1));
        });

        thread2.start();
    }

}
