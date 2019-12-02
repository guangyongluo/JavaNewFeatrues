package com.vilin.lambda;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

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
}
