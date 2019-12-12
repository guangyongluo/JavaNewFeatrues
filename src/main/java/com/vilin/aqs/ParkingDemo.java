package com.vilin.aqs;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class ParkingDemo {

    public static void main(String[] args) {
        //创建Semaphore信号量
        Semaphore sp = new Semaphore(5);

        Thread[] cars = new Thread[10];
        for(int i = 0; i < cars.length; i++){
             cars[i] = new Thread(() -> {
                 try {
                     TimeUnit.SECONDS.sleep(2);
                     sp.acquire();
                     System.out.println(Thread.currentThread().getName() + "可以进停车场");
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }

                 try {
                     TimeUnit.SECONDS.sleep(new Random().nextInt(10));
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }

                 try {
                     sp.release();
                     System.out.println(Thread.currentThread().getName() + "离开停车场");
                 } catch (Exception e) {
                     e.printStackTrace();
                 }

             }, "cars[" + i + "]");

             cars[i].start();
        }
    }
}
