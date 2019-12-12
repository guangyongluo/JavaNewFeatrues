package com.vilin.aqs;

import java.util.concurrent.locks.ReentrantLock;

public class LockDemo3 {
    private ReentrantLock lock = new ReentrantLock();
    private int m = 0;

    public void a(){
        lock.lock();
        try {
            System.out.println("a");
            b();
        } finally {
            lock.unlock();
        }
    }

    public void b(){
        lock.lock();
        try {
            System.out.println("b");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        LockDemo3 demo = new LockDemo3();
        new Thread(() ->{
            demo.a();
        }).start();
    }

}
