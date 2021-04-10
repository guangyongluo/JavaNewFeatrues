package com.vilin.lambda;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Java8 内置的四大核心函数式接口
 *
 * 1. Consumer<T>:消费型接口
 *     void accept(T t);
 * 2. Supplier<T>:共计型接口
 *     T get();
 * 3. Function<T, R>:函数型接口
 *     R apply(T t);
 * 4. Predicate<T>: 断言型接口
 *     boolean test(T t);
 */
public class LambdaTest5 {

    //Consumer<T>:消费型接口
    @Test
    public void test1(){
        happy(1000, (m) -> System.out.println("消费：" + m + "元"));
    }

    public void happy(double money, Consumer<Double> consumer){
        consumer.accept(money);
    }

    //Supplier<T>:共计型接口
    @Test
    public void test2(){
        List<Integer> numList = getNumList(10, () -> (int)(Math.random() * 100));
        for (Integer num : numList) {
            System.out.println(num);
        }
    }

    //需求：产生指定个数的整数，并放入集合中
    public List<Integer> getNumList(int num, Supplier<Integer> supplier){
        List<Integer> list = new ArrayList<>();

        for(int i = 0; i < num; i++){
            Integer number = supplier.get();
            list.add(number);
        }

        return list;
    }

    //Function<T, R>:函数型接口
    @Test
    public void test3(){
        System.out.println(stringHandler("\t\t\t hello     ", str -> str.trim()));
    }

    //需求：用于处理字符串
    public String stringHandler(String str, Function<String, String> function){
        return function.apply(str);
    }

    //Predicate<T>: 断言型接口
    @Test
    public void test(){
        List<String> list = Arrays.asList("Hello", "vilin", "lambda", "www", "ok");
        List<String> strings = filterString(list, (str) -> str.length() > 5);

        for (String string : strings){
            System.out.println(string);
        }
    }

    //需求：将满足条件的字符串放入集合中
    public List<String> filterString(List<String> list, Predicate<String> predicate){
        List<String> strings = new ArrayList<>();
        for(String str : list){
            if(predicate.test(str)){
                strings.add(str);
            }
        }
        return strings;
    }
}
