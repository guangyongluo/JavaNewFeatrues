package com.vilin.annotation;

import java.util.ArrayList;
import java.util.List;

public class Son extends Parent {

//    @Override
    public void fan(){
        System.out.println("son override parent fun method.");
    }

    public void warning(){
        @SuppressWarnings("")
        List list = new ArrayList();
        list.add("IBM");
        list.add(123);
    }
}
