package com.vilin.thread;

import java.util.concurrent.TimeUnit;

public class SyncDemo01 {

    public synchronized static void accessResources1() {
        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println(Thread.currentThread().getName() + " is running");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void accessResources2(){
        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println(Thread.currentThread().getName() + " is running");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void accessResources3(){
        synchronized (this) {
            try {
                TimeUnit.MINUTES.sleep(2);
                System.out.println(Thread.currentThread().getName() + " is running");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void accessResources4(){
        synchronized (SyncDemo01.class) {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + " is running");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SyncDemo01 syncDemo01 = new SyncDemo01();
        for(int i = 0; i < 5; i++){
            new Thread(syncDemo01::accessResources3).start();
        }
    }
}
