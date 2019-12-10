package com.vilin.thread;

import java.util.concurrent.TimeUnit;

public class ReaderAndWriter {

    private final static int MAX_NUM = 50;

    private volatile static int initValue = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            int localValue = initValue;
            while(localValue < MAX_NUM){
                if(localValue != initValue){
                    System.out.println("Reader : " + initValue);
                    localValue=initValue;
                }
            }
        }, "Reader").start();

        new Thread(() -> {
            int localValue = initValue;
            while(localValue < MAX_NUM){
                System.out.println("Writer : " + (++localValue));
                initValue = localValue;
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
