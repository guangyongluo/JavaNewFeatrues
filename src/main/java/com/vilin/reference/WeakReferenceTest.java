package com.vilin.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.WeakReference;

public class WeakReferenceTest {
    public static void main(String[] args) {
        WeakReference weakReference = new WeakReference<>(new Reference());

        System.out.println(weakReference.get());
        System.gc();
        System.out.println(weakReference.get());

    }
}
