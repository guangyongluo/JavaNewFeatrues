package com.vilin.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PhantomReferenceTest {

    private static final List<byte[]> LIST = new ArrayList<>();

    private static final ReferenceQueue<Reference> QUEUE = new ReferenceQueue<>();

    public static void main(String[] args) {

        PhantomReference<Reference> phantomReference = new PhantomReference<>(new Reference(), QUEUE);

        new Thread(() -> {
            while(true){
                LIST.add(new byte[1024 * 1024]);

                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }

                System.out.println(phantomReference.get());
            }
        }).start();


        new Thread(() -> {
            while(true){
                java.lang.ref.Reference<? extends Reference> poll = QUEUE.poll();
                if(poll != null){
                    System.out.println("phantom reference is clear by GC : " + poll);
                }
            }
        }).start();

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
