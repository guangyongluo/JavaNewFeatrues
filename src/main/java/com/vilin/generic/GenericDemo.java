package com.vilin.generic;

import java.util.Collection;

public class GenericDemo<T> {

    public static void printConllection(Collection<? extends Number> collection){
        for(Number item : collection){
            System.out.println(item);
        }
    }

    public static <T> void printCollection(Collection<T> collection){
        for(T item : collection){
            System.out.println(item);
        }
    }

    public static void main(String[] args) {
        Parent.printType(100);
    }
}
