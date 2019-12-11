package com.vilin.thread;

import java.util.concurrent.TimeUnit;

public class IncreaseDemo {

    private static volatile int i  =0;

    public synchronized static void increase(){
        i ++;
        System.out.println(i);
    }

    public static void main(String[] args) {
        for(int i = 0; i < 10; i++){
            new Thread(() -> {
                for (int j = 0; j < 5; j++){
                    increase();
                }
            }).start();
        }

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("result : " + i);
    }
}
