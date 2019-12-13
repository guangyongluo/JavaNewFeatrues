package com.vilin.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadDemo {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newSingleThreadExecutor();

        //创建10个任务，然后把任务交给pool
        for(int i = 0; i < 10; i++){
            Runnable task = new Task();
            //把任务交给线程池
            pool.execute(task);
        }

        pool.shutdown();
    }
}
