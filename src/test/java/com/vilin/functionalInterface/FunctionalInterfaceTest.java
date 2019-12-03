package com.vilin.functionalInterface;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterfaceTest {

    //消费型
    @Test
    public void test1(){
        fun("helloworld", (x) -> System.out.println(x.substring(3)));
    }

    private void fun(String s, Consumer<String> consumer){
        consumer.accept(s);
    }

    //供给型
    @Test
    public void test2(){
        List<Double> randomNumlist = generateRandomNumList(10, () -> Math.random() * 100);
        randomNumlist.stream().forEach(System.out::println);
    }

    private List<Double> generateRandomNumList(int num, Supplier<Double> supplier){
        List<Double> randomNumList = new ArrayList<Double>();

        for (int i = 0; i < num; i++){
            Double randomNum = supplier.get();
            randomNumList.add(randomNum);
        }

        return randomNumList;
    }

    //函数型
    @Test
    public void test3(){
        String s = "helloworld";

        String result = handler(s, (x) -> x.toUpperCase());
        System.out.println(result);
    }

    private String handler(String s, Function<String, String> function){
        return function.apply(s);
    }

    //断言型
    @Test
    public void test4(){
        List<String> data = new ArrayList<String>();
        data.add("IBM");
        data.add("SUN");
        data.add("Oracle");
        data.add("Google");
        data.add("Apple");

        List<String> list = filterList(data, s -> s.length() == 3);
        list.stream().forEach(System.out::println);
    }

    private List<String> filterList(List<String> list, Predicate<String> predicate){
        List<String> resultList = new ArrayList<String>();

        for(String s : list){
            if(predicate.test(s)){
                resultList.add(s);
            }
        }

        return resultList;
    }
}
