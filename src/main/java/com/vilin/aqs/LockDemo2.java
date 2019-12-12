package com.vilin.aqs;

public class LockDemo2 {
    private MyLock lock = new MyLock();
    private int m = 0;

    public void a(){
        lock.lock();
        System.out.println("a");
        b();
        lock.unlock();
    }

    public void b(){
        lock.lock();
        System.out.println("b");
        lock.unlock();
    }

    public static void main(String[] args) {
        LockDemo2 demo = new LockDemo2();
        new Thread(() ->{
            demo.a();
        }).start();
    }

}
