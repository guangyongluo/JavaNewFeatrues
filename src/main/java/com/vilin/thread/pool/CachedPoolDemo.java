package com.vilin.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedPoolDemo {

    public static void main(String[] args) {
        //穿件可变大小的线程池
        ExecutorService pool = Executors.newCachedThreadPool();

        //创建10个任务，然后把任务交给pool
        for(int i = 0; i < 10; i++){
            Runnable task = new Task();
            //把任务交给线程池
            pool.execute(task);
        }

        pool.shutdown();
    }
}
