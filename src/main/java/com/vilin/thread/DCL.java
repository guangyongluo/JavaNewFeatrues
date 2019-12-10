package com.vilin.thread;

/**
 * double check
 */

public class DCL {

    private volatile DCL  instance = null;

    private DCL(){}

    public DCL getInstance(){
        if(instance == null){
            synchronized (DCL.class){
                if(instance == null) {
                    instance = new DCL();
                }
            }
        }

        return instance;
    }


}
