package com.vilin.lambda;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 一、方法引用：若Lambda体中的内容已经有方法实现了，我们可以使用"方法引用"
 * (可以理解为方法引用是Lambda表达式的另外一种表现形式)
 *
 * 主要有三种语法格式：
 *
 * 对象::实例方法名
 *
 * 类::静态方法名
 *
 * 类::实例方法名
 *
 * 注意：
 *  1、Lambda体中调用方法的参数列表和返回值类型，要与函数式接口中抽象方法的函数列表和返回值类型保持一致
 *  2、若Lambda参数列表中的第一个参数是实例方法的调用者，而第二个参数是实例方法的参数时，可以使用className::method
 *
 *  二、构造器引用
 *
 *  格式： className::new
 *
 *  注意：需要调用的构造器列表要与函数式接口中抽象方法的参数列表保存一致！
 *
 *  三、数组引用
 *
 *  Type::new
 */

public class TestMethodReference {

    //对象::实方法名
    @Test
    public void test1(){
        Consumer<String> consumer = System.out::println;

        consumer.accept("abc");
    }

    @Test
    public void test2(){
        Employee employee = new Employee(100001, 38, "Leo", 30000.00);
        Supplier<Integer> supplier = () -> employee.getAge();

        Supplier<Integer> supplier1 = employee::getAge;
    }

    //类::静态方法名
    @Test
    public void test3(){
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);

        Comparator<Integer> comparator1 = Integer::compare;
    }

    //类::实例方法名
    @Test
    public void test4(){
        BiPredicate<String, String> biPredicate = (x, y) -> x.equals(y);

        BiPredicate<String, String> biPredicate1 = String::equals;
    }

    //构造器引用
    @Test
    public void test5(){
        Supplier<Employee> supplier = () -> new Employee(100001, 38, "Leo", 30000.00);

        //无参构造器
        Supplier<Employee> supplier1 = Employee::new;
    }

    @Test
    public void test6(){
        Function<Integer, Employee> function = (x) -> new Employee(x);

        Function<Integer, Employee> function1 = Employee::new;

        Employee emp = function1.apply(10001);
        System.out.println(emp);
    }

    //数组引用
    @Test
    public void test7(){
        Function<Integer, String[]> fun = x -> new String[x];
        String[] strings = fun.apply(10);
        System.out.println(strings.length);

        Function<Integer, String[]> function = String[]::new;
        String[] strs = function.apply(20);
        System.out.println(strs.length);

    }
}
