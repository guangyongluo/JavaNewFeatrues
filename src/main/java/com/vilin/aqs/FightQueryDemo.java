package com.vilin.aqs;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class FightQueryDemo {
    private static List<String> companys = Arrays.asList("东方航空", "南方航空", "中国航空");
    private static List<String> fightList = new ArrayList<String>();

    public static void main(String[] args) throws InterruptedException {
        String origin = "BJ";
        String dest = "SH";

        Thread[] threads = new Thread[companys.size()];
        CountDownLatch latch = new CountDownLatch(companys.size());

        for(int i = 0; i < threads.length; i++){
            String name = companys.get(i);

            threads[i] = new Thread(() -> {
                System.out.printf("%s 查询从%s到%s的机票\n", name, origin, dest);
                //随机产生票数
                int val = new Random().nextInt(10);

                try {
                    TimeUnit.SECONDS.sleep(val);
                    fightList.add(name + "---" + val);
                    System.out.printf("%s公司查询成功！\n", name);
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            threads[i].start();
        }

        latch.await();
        System.out.println("=======查询结构如下======");
        fightList.forEach(System.out::println);
    }
}
