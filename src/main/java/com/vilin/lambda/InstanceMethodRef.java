package com.vilin.lambda;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class InstanceMethodRef extends Base{

    public String put(){
        return "hello";
    }

    public void con(int size){
        System.out.println("invoke InstanceMethodRef object method con");
    }

    public String toUpperCase(String s){
        System.out.println("current class toUpperCase method");
        return s.toUpperCase();
    }

    public Integer len(String s1, String s2){
        return s1.length() + s2.length();
    }

    public void test(){
        Function<String, String> function1 = this::toUpperCase;
        System.out.println(function1.apply("admin"));
        Function<String, String> function2 = super::toUpperCase;
        System.out.println(function2.apply("password"));
    }

    public static void main(String[] args) {

        InstanceMethodRef instance = new InstanceMethodRef();

        Supplier<String> supplier = instance::put;
        System.out.println(supplier.get());

        Consumer<Integer> consumer = instance::con;
        consumer.accept(100);

        Function<String, String> function = instance::toUpperCase;
        System.out.println(function.apply("hello, world"));

        BiFunction<String, String, Integer> biFunction = instance::len;
        System.out.println(biFunction.apply("hello", "world"));

        instance.test();
    }
}

class Base{
    public String toUpperCase(String s){
        System.out.println("super class toUpperCase method");
        return s.toUpperCase();
    }
}