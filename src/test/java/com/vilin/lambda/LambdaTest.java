package com.vilin.lambda;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Consumer;

public class LambdaTest {

    @Test
    public void test(){
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        };

        TreeSet<String> treeSet = new TreeSet<String>(comparator);
        treeSet.add("zhangsan");
        treeSet.add("lisi");
        treeSet.add("wangwu");
        treeSet.add("C罗");

        for(String s : treeSet){
            System.out.println(s);
        }
    }

    @Test
    public void test1(){
        Comparator<String> comparator = (x, y) -> Integer.compare(x.length(), y.length());
        TreeSet<String> treeSet = new TreeSet<String>(comparator);
        treeSet.add("zhangsan");
        treeSet.add("lisi");
        treeSet.add("wangwu");
        treeSet.add("C罗");

        for(String s : treeSet){
            System.out.println(s);
        }
    }

    private List<User> userList;

    private UserService userService;

    @BeforeEach
    public void setUp(){
        this.userList = new ArrayList<User>();
        this.userService = new UserService();
        userList.add(new User(1, "zhangsan", "admin", 1234.45F, new Date()));
        userList.add(new User(2, "lisi", "admin", 123.45F, new Date()));
        userList.add(new User(3, "wangwu", "admin", 2345.45F, new Date()));
        userList.add(new User(4, "C罗", "admin", 9567.45F, new Date()));
        userList.add(new User(5, "本泽马", "admin", 789.45F, new Date()));

    }

    @Test
    public void test2(){
        List<User> tempList = new ArrayList<User>();

        for(User user : userList){
            if(user.getSalary() > 1000.00F){
                tempList.add(user);
            }
        }

        for(User user : tempList){
            System.out.println(user);
        }
    }

    @Test
    public void test3(){
        List<User> list = this.userService.filterUser(this.userList, new MyPredicate<User>() {
            @Override
            public boolean test(User user) {
                return user.getSalary() > 1000.00F;
            }
        });

        for(User user : list){
            System.out.println(user);
        }
    }

    @Test
    public void test4(){
        List<User> list = this.userService.filterUser(this.userList, (user) -> user.getSalary() > 1000.00F);
        for(User user : list){
            System.out.println(user);
        }
    }

    //lambda表达式-无参无返回值
    @Test
    public void test5(){
        Runnable runnable = () -> System.out.println("hello world");
        runnable.run();

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world");
            }
        };

        runnable1.run();
    }

    //lambda表达式-有参无返回值
    @Test
    public void test6(){
        Consumer<String> consumer = (x) -> System.out.println(x);

    }

    //lambda表达式-有参无返回值，如果只有一个参数，参数的小括号可以省略
    @Test
    public void test7(){
        Consumer<String> consumer = x -> System.out.println(x);
    }

    //lambda表达式，有多个参数且有返回值
    @Test
    public void test8(){
        Comparator<Integer> comparator = (x, y) -> {
          return Integer.compare(x, y);
        };

        System.out.println(comparator.compare(12, 11));
    }

    //lambda表达式，有多个参数且有返回值,但是只有一行代码的话，花括号可以省略
    @Test
    public void test9(){
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);

        System.out.println(comparator.compare(12, 11));
    }

    //lambda表达式，
    @Test
    public void test10(){

    }
}
