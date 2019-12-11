package com.vilin.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo01 {

    private volatile static int m = 0;

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void increase1(){
        m++;
    }

    public static void increase2(){
        System.out.println(atomicInteger.incrementAndGet());
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[20];

        for(int i = 0; i < 20; i++){
            threads[i] =  new Thread(() ->{
                CASDemo01.increase1();
            });
            threads[i].start();
            threads[i].join();// join方法后加入group
        }

        System.out.println(m);



        for(int i = 0; i < 20; i++){
            threads[i] = new Thread(() ->{
                CASDemo01.increase2();
            });
            threads[i].start();
//            threads[i].join();
        }

        TimeUnit.SECONDS.sleep(3);
        System.out.println("atomic : " + atomicInteger.get());
    }
}
