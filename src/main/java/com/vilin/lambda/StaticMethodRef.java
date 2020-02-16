package com.vilin.lambda;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class StaticMethodRef {
    public static String put(){
        System.out.println("invoke StaticMethodRef class static method");
        return "hello";
    }

    public static void con(int size){
        System.out.println("size : " + size);
    }

    public static String toUpperCase(String s){
        return s.toUpperCase();
    }

    public static Integer len(String str1, String str2){
        return str1.length() + str2.length();
    }

    public static void main(String[] args) {

        Supplier<String> s1 = () -> put();
        System.out.println(s1.get());
        Supplier<String> s2 = StaticMethodRef::put;
        System.out.println(s2.get());
        Supplier<String> s3 = Foo::ret;
        System.out.println(s3.get());

        Consumer<Integer> c1 = (size) -> con(size);
        c1.accept(1);
        Consumer<Integer> c2 = StaticMethodRef::con;
        c2.accept(2);

        Function<String, String> f1 = s -> toUpperCase(s);
        Function<String, String> f2 = s -> StaticMethodRef.toUpperCase(s);
        Function<String, String> f3 = String::toUpperCase;
        System.out.println(f1.apply("hello"));
        System.out.println(f2.apply("hello"));
        System.out.println(f3.apply("hello"));

        BiFunction<String, String, Integer> bf1 = (str1, str2) -> str1.length() + str2.length();
        BiFunction<String, String, Integer> bf2 = (str1, str2) -> StaticMethodRef.len(str1, str2);
        BiFunction<String, String, Integer> bf3 = (str1, str2) -> len(str1, str2);
        BiFunction<String, String, Integer> bf4 = StaticMethodRef::len;
        System.out.println(bf1.apply("hello", "world"));
        System.out.println(bf2.apply("hello", "world"));
        System.out.println(bf3.apply("hello", "world"));
        System.out.println(bf4.apply("hello", "world"));

    }
}

class Foo{
    public static String ret(){
        System.out.println("invoke Foo class static method");
        return "world";
    }
}
