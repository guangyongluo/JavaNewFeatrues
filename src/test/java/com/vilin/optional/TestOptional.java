package com.vilin.optional;


import com.vilin.lambda.Employee;
import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * Optional容器类的常用方法：
 * of(T t):创建一个Optional类的实例；
 * empty():创建一个空的Optional实例；
 * ofNullable(T t):若t不为null，则创建Optional实例，否则创建一个空实例；
 * isPresent():判断是否包含值；
 * orElse(T t):如果调用对象包含值则返回该值，否则返回T；
 * orElseGet(Supplier s):如果调用对象包含值则返回该值，否则返回s获得的值;
 * map(Function f):如果有值则对其进行处理,并返回处理后的Optional，否则返回Optional.empty();
 * flapMap(Function f):跟map相似，但必须返回Optional.
 */
public class TestOptional {

    @Test
    public void test1(){
        Optional<Employee> optional = Optional.of(new Employee());
        System.out.println(optional.get());
    }

    @Test
    public void test2(){
        Optional<Employee> empty = Optional.empty();
        System.out.println(empty.get());
    }

    @Test
    public void test3(){
        Optional<Employee> employee = Optional.ofNullable(null);
        if(employee.isPresent()){
            System.out.println(employee.get());
        }

//        Employee emp = employee.orElse(new Employee(100001, 23, "张三", 300000.00, Employee.Status.BUSY));

        Employee emp = employee.orElseGet(() -> new Employee());

        System.out.println(emp);
    }

    @Test
    public void test4(){
        Optional<Employee> opt = Optional.ofNullable(new Employee(100001, 23, "张三", 300000.00, Employee.Status.BUSY));

        Optional<String> name = opt.map((e) -> e.getName());
        System.out.println(name.get());

        Optional<String> s = opt.flatMap((e) -> Optional.of(e.getName()));
        System.out.println(s.get());
    }

    @Test
    public void test5(){
        Man man = new Man();
        String name = getManGodness(man);
        System.out.println(name);
    }

    public String getManGodness(Man man){
        return man.getGodness().getName();
    }

    @Test
    public void test6(){
        Optional<NewMan> newMan = Optional.ofNullable(null);
        String manGodness2 = getManGodness2(newMan);
        System.out.println(manGodness2);
    }

    public String getManGodness2(Optional<NewMan> NewMan){
        return NewMan.orElse(new NewMan()).getGodness().orElse(new Godness("苍老师")).getName();
    }
}
