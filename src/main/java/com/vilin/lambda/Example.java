package com.vilin.lambda;

import java.util.function.Consumer;
import java.util.function.Function;

public class Example {

    public static void main(String[] args) {
        Function<String, String> function = a -> a.toUpperCase();
        System.out.println(function.apply("admin"));

        Consumer<String> consumer = a -> System.out.println(a);
        consumer.accept("admin");
    }
}
