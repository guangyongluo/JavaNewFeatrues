package com.vilin.thread;

public class HolderDemo {
    private HolderDemo(){}

    //懒加载
    //没有使用代码同步Synchronized.
    private static class Holder{
        private static HolderDemo instance = new HolderDemo();
    }

    public static HolderDemo getInstance(){
        return Holder.instance;
    }
}
