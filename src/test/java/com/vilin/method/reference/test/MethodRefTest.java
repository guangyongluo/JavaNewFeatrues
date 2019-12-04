package com.vilin.method.reference.test;

import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.Consumer;

public class MethodRefTest {

    @Test
    public void test1(){
        PrintStream printStream = System.out;
        Consumer<String> consumer = s -> printStream.println(s);
        consumer.accept("helloworld");

        consumer = printStream::println;
        consumer.accept("你好");
    }

    @Test
    public void test2(){
        Comparator<Integer> comparator = Integer::compare;
        System.out.println(comparator.compare(10, 11));
    }
}
