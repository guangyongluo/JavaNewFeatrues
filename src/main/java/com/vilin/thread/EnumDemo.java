package com.vilin.thread;

public enum EnumDemo {
    // 常量，在加载的时候被实例化一次
    A,B,C,D;

    public void method1(){
        System.out.println("method1");
    }

    public static void main(String[] args) {
        A.method1();
        B.method1();
        C.method1();
        D.method1();
        System.out.println(A.getClass().getName());
        System.out.println(B.getClass().getName());
        System.out.println(C.getClass().getName());
        System.out.println(D.getClass().getName());

    }
}
