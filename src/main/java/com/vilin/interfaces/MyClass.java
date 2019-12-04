package com.vilin.interfaces;

public class MyClass implements InterfaceB, InterfaceC {

    @Override
    public String showInfo() {
//        return InterfaceC.super.showInfo();
        return "Overwrite showInfo() by class";
    }

    public static void main(String[] args) {
        MyClass clazz = new MyClass();
        System.out.println(clazz.showInfo());
    }
}
