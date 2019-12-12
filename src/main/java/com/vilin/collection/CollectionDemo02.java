package com.vilin.collection;

import org.omg.CORBA.TIMEOUT;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class CollectionDemo02 {
    public static void main(String[] args) {
        LinkedBlockingDeque<String> list = new LinkedBlockingDeque<String>(3);
        Thread thread = new Thread(() -> {
           for(int i = 0; i < 3; i++){
               for(int j = 0; j < 5; j++){
                   String str = new String(i + " : " + j);
                   try {
                       list.put(str.toString());
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
                   System.out.println("client : " + str + new Date());
               }
           }
        });
        thread.start();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 3; j++){
                try {
                    String str = list.take();
                    System.out.println("main:take " + str + " size : " + list.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("end");
    }
}
