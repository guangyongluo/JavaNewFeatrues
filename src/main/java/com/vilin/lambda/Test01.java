package com.vilin.lambda;

import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Test01 {

    public static void main(String[] args) throws Exception{
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("r1 running");
            }
        };
        r1.run();

        Runnable r2 = () -> {
            System.out.println("r2 running");
        };
        r2.run();

        Runnable r3 = () -> System.out.println("r3 running");
        r3.run();

        Callable<String> c1 = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "hello";
            }
        };
        System.out.println(c1.call());

        Callable<String> c2 = () -> {return "hello";};
        System.out.println(c2.call());

        Callable<String> c3 = () -> "hello";
        System.out.println(c3.call());


        UserMapper userMapper1 = new UserMapper() {
            @Override
            public void insertUser(User user) {
                System.out.println("insert User : " + user);
            }
        };

        UserMapper userMapper2 = (User user) -> {
            System.out.println("insert User : " + user);
        };

        UserMapper userMapper3 = (user) -> System.out.println("insert User : " + user);

        userMapper1.insertUser(new User());
        userMapper2.insertUser(new User());
        userMapper3.insertUser(new User());


        OrderMapper orderMapper1 = new OrderMapper() {
            @Override
            public int insertOrder(Order order) {
                System.out.println("insert Order : " + order);
                return 1;
            }
        };
        OrderMapper orderMapper2 = (Order order) -> {return 1;};
        OrderMapper orderMapper3 = (order) -> 1;

        Function<Integer, Integer> function = n -> {
            int total = 0;
            for(int i = 1; i <= n; i++){
                total += i;
            }
            return total;
        };

        System.out.println(function.apply(10));

        BiFunction<String, String, Integer> biFunction = (str1, str2) -> {
            return str1.length() + str2.length();
        };

        System.out.println(biFunction.apply("123","321"));
    }

}

interface UserMapper{
    void insertUser(User user);
}

interface OrderMapper{
    int insertOrder(Order order);
}

class Order{

}

