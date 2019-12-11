package com.vilin.thread;

public class EnumSingletonDemo {
    private EnumSingletonDemo(){}

    private enum EnumHolder{
        INSTANCE;
        private EnumSingletonDemo instance;
        EnumHolder(){
            this.instance = new EnumSingletonDemo();
        }
        private EnumSingletonDemo getInstance(){
            return instance;
        }
    }

    public EnumSingletonDemo getInstance(){
        return EnumHolder.INSTANCE.instance;
    }
}
