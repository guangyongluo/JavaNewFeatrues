package com.vilin.stream;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest1 {
    public static void gen1(){
        String[] stringArray = {"a", "b", "c", "d", "e"};
        Stream<String> stream = Stream.of(stringArray);
        stream.forEach(System.out::println);
    }

    public static void gen2(){
        List<String> stringList = Arrays.asList("a", "b", "c", "d", "e");
        Stream<String> stream = stringList.stream();
        stream.forEach(System.out::println);
    }

    public static void gen3(){
        Stream<Integer> stream = Stream.generate(() -> 1);
        stream.limit(10).forEach(System.out::println);
    }

    public static void gen4(){
        Stream<Integer> stream = Stream.iterate(1, x -> x + 1);
        stream.limit(10).forEach(System.out::println);
    }

    public static void gen5(){
        String s = "12345";
        IntStream intStream = s.chars();
        intStream.forEach(System.out::println);
    }

    public static void gen6(){
        try {
            Files.lines(Paths.get("D:/FormValidation教案.txt")).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        gen1();
//        gen2();
//        gen3();
//        gen4();
//        gen5();
//        gen6();

//        Arrays.asList(1,2,3,4,5).stream().filter(x -> x % 2 == 0).forEach(System.out::println);
//
//        int sum = Arrays.asList(1,2,3,4,5).stream().mapToInt(x -> x).sum();
//        System.out.println("sum = " + sum);
//
//        int max = Arrays.asList(1,2,3,4,5).stream().max((a, b) -> a - b).get();
//        System.out.println("max = " + max);
//
//        int min = Arrays.asList(1,2,3,4,5).stream().min((a, b) -> a - b).get();
//        System.out.println("min = " + min);
//
//        Optional optional = Arrays.asList(1,2,3,4,5).stream().filter(x -> x % 2 == 0).findAny();
//        System.out.println("findAny = " + optional.get());
//
//        optional = Arrays.asList(1,2,3,4,5).stream().filter(x -> x % 2 == 0).findFirst();
//        System.out.println("findFirst = " + optional.get());
//
//        optional = Arrays.asList(1,2,3,4,5,6).stream().filter(x -> x % 2 == 0).sorted((a, b) -> b -a).findFirst();
//        System.out.println("findFirstBySortDesc = " + optional.get());

//        Arrays.asList(8,3,6,9,1,23).stream().sorted().forEach(System.out::print);
//
//        System.out.println();
//        Arrays.asList(8,3,6,9,1,23).stream().sorted((a, b) -> b - a).forEach(System.out::print);

//        Arrays.asList("a", "abcd", "abc", "abcde", "ab").stream().sorted((a, b) -> a.length() - b.length()).forEach(System.out::println);

//        List<Integer> list = Stream.iterate(1, x -> x+1).limit(50).collect(Collectors.toList());
//        System.out.println(list);

//        Arrays.asList(3, 8, 5, 8, 3, 1, 2, 1).stream().distinct().forEach(System.out::println);

//        Set<Integer> set = Arrays.asList(3, 8, 5, 8, 3, 1, 2, 1).stream().collect(Collectors.toSet());
//        System.out.println(set);

//        String str1 = "11,22,33,44,55";
//        int sum = Stream.of(str1.split(",")).mapToInt(Integer::valueOf).sum();
//        System.out.println("sum = " + sum);

//        String str2 = "Leo,Ann,Jack,Tom,Eric";
//        List<Customer> list = Stream.of(str2.split(",")).map(Customer::new).collect(Collectors.toList());
//        System.out.println(list);
//
//        Set<Customer> set = Stream.of(str2.split(",")).peek(System.out::println).map(Customer::new).collect(Collectors.toSet());
//        System.out.println(set);


        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "4");
        Optional<Integer> max = Stream.iterate(1, x -> x + 1).limit(200).peek(x -> {
            System.out.println(Thread.currentThread().getName());
        }).parallel().max(Integer::compareTo);

        System.out.println("max = " + max.get());
    }
}

class Customer{
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                '}';
    }
}