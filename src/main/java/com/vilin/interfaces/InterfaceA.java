package com.vilin.interfaces;

public interface InterfaceA {
    String USER_INFO = "不祥";

    void fun();

    default String showInfo(){
        return "hello world";
    }

    static void service(){
        System.out.println("服务");
    }
}
