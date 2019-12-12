package com.vilin.collection;

import java.util.concurrent.ConcurrentLinkedDeque;

public class CollectionDemo01 {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentLinkedDeque<String> list = new ConcurrentLinkedDeque<String>();

        //添加数据
        Thread[] addThreads = new Thread[100];
        for(int i = 0; i < addThreads.length; i++){
            addThreads[i] = new Thread(() -> {
                for(int j = 0; j < 10000; j++){
                    list.add(Thread.currentThread().getName() + ":Element " + j);
                }
            });
            addThreads[i].start();
            addThreads[i].join();
        }

        System.out.println(" After add size : " + list.size());

        //移除数据
        Thread[] pollThreads = new Thread[100];
        for(int i = 0; i < pollThreads.length; i++){
            pollThreads[i] = new Thread(() -> {
                for(int j = 0; j < 5000; j++){
                    list.pollLast();
                    list.pollFirst();
                }
            });
            pollThreads[i].start();
            pollThreads[i].join();
        }

        System.out.println("After poll size" + list.size());
    }
}
