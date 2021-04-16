package com.vilin.forkjoin;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class TestForkJoin {

    public static void main(String[] args) {

        Instant start = Instant.now();

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> forkJoinTask = new ForkJoinCalculate(0, 1000000000L);
        long sum = forkJoinPool.invoke(forkJoinTask);
        System.out.println(sum);

        Instant end = Instant.now();

        System.out.println("耗费时间:" + Duration.between(start, end).toMillis());


        start = Instant.now();

        for(long i = 0; i < 1000000000; i++){
            sum += i;
        }

        end = Instant.now();

        System.out.println("耗费时间:" + Duration.between(start, end).toMillis());
    }



}
