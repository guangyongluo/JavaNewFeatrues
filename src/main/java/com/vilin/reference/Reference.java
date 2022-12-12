package com.vilin.reference;

public class Reference {

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
        super.finalize();

    }
}
