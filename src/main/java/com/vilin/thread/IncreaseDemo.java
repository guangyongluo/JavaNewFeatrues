package com.vilin.thread;

public class IncreaseDemo {

    private static volatile int i  =0;

    public synchronized static void increase(){
        i ++;
    }

    public static void main(String[] args) {
        for(int i = 0; i < 10; i++){
            new Thread(() -> {
                for (int j = 0; j < 5; j++){
                    increase();
                }
            }).start();
        }

        System.out.println(i);
    }
}
