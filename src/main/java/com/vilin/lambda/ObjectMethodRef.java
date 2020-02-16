package com.vilin.lambda;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class ObjectMethodRef {

    public static void main(String[] args) {
        Consumer<Too> consumer1 = (too) -> new Too().foo();
        Consumer<Too> consumer2 = Too::foo;

        consumer1.accept(new Too());
        consumer2.accept(new Too());

        BiConsumer<Too2, String> bc1 = (too2, str) -> new Too2().foo(str);
        BiConsumer<Too2, String> bc2 = Too2::foo;

        bc1.accept(new Too2(), "hello");
        bc2.accept(new Too2(), "hello");

        BiFunction<Prod, String, Integer> bf1 = (prod, str) -> new Prod().fun(str);
        BiFunction<Prod, String, Integer> bf2 = Prod::fun;

        bf1.apply(new Prod(), "world");
        bf2.apply(new Prod(), "world");

        Execute<Prod, String, String> execute1 = (prod, str1, str2) -> new Prod().check(str1, str2);
        Execute<Prod, String, String> execute2 = Prod::check;
        System.out.println(execute1.run(new Prod(), "hello", "world"));
        System.out.println(execute2.run(new Prod(), "hello", "HelLO"));
    }
}

class Too{
    public void foo(){
        System.out.println("invoke class Too method foo()");
    }
}

class Too2{
    public void foo(String s){
        System.out.println(s);
    }
}

class Prod{
    public Integer fun(String s){
        System.out.println(s);
        return 1;
    }

    public boolean check(String str1, String str2){
        return str1.equalsIgnoreCase(str2);
    }
}

interface Execute<U, T, V>{
    public boolean run(U u, T t, V v);
}