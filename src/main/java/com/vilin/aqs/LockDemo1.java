package com.vilin.aqs;

public class LockDemo1 {
    private MyLock lock = new MyLock();
    private int m = 0;

    public int next(){
//        try {
//            TimeUnit.SECONDS.sleep(2);
//            return m++;
//        } catch (InterruptedException e) {
//            throw new RuntimeException("ERROR");
//        }
        lock.lock();
        try {
            return m++;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        LockDemo1 demo = new LockDemo1();
        Thread[] threads = new Thread[20];
        for(int i = 0; i < 20; i++){
            threads[i] = new Thread(() -> {
                System.out.println(demo.next());
            });
            threads[i].start();
        }
    }

}
