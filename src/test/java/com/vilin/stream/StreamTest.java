package com.vilin.stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamTest {

    private List<User> userList;

    @BeforeEach
    public void setUP(){
        User user1 = new User();
        user1.setId(1);
        user1.setName("zhangsan");
        user1.setSalary(123.45F);

        User user2 = new User();
        user2.setId(2);
        user2.setName("lisi");
        user2.setSalary(56.55F);

        User user3 = new User();
        user3.setId(1);
        user3.setName("wangwu");
        user3.setSalary(67.88F);

        this.userList = new ArrayList<User>();
        this.userList.add(user1);
        this.userList.add(user2);
        this.userList.add(user3);
    }

    @Test
    public void test1(){
        List<String> list = new ArrayList<String>();
        Stream<String> stream = list.stream();
        Stream<String> parallelStream = list.parallelStream();

        String[] arrayDemo = new String[]{"IBM","Oracle","Microsoft","Google","Apple"};
        Stream<String> arrayStream = Arrays.stream(arrayDemo);

        Stream<String> stringStream = Stream.of("zhangsan","lisi","wangwu");

        Stream<Double> doubleStream = Stream.generate(Math::random).limit(5);
    }

    @Test
    public void test2(){
        String[] arrayDemo = new String[]{"IBM","Oracle","Microsoft","Google","Apple"};
        Stream<String> arrayStream = Arrays.stream(arrayDemo);

        arrayStream.filter((x) ->{
            if(x.length() == 3)
                return true;
            else
                return false;
        }).forEach(System.out::println);
    }

    @Test
    public void test3(){
        String[] arrayDemo = new String[]{"IBM", "SUN", "Oracle","Microsoft","Google","Apple","EMC"};
        Stream<String> arrayStream = Arrays.stream(arrayDemo);

        arrayStream.filter((x) ->{
            if(x.length() == 3)
                return true;
            else
                return false;
        }).limit(2).forEach(System.out::println);
    }

    @Test
    public void test4(){
        List<String> list = new ArrayList<String>();
        list.add("abc");
        list.add("def");
        list.add("ghi");
        list.add("jlm");
        list.add("nop");

        list.stream().map(x -> x.toUpperCase()).forEach(System.out::println);

    }

    @Test
    public void test5(){
        this.userList.stream().map( u -> u.getName()).sorted().forEach(System.out::println);
    }

    @Test
    public void test6(){
        this.userList.stream().sorted((u1, u2) -> u1.getSalary().compareTo(u2.getSalary())).map(u -> u.getName()).forEach(System.out::println);
    }
}
