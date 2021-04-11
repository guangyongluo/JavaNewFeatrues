package com.vilin.stream;

import com.vilin.lambda.Employee;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Stream;

/**
 * 中间操作：不会执行任何操作
 *
 * 筛选与切片
 * filter: 接受Lambda，从流中排出某些元素；
 * limit(n): 截断流，使其元素不超过给定数量；
 * skip(n): 跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回一个空流。与limit(n)互补
 * distinct: 筛选，通过流所生成元素的hashCode()和equals去除重复元素
 */
public class TestStreamAPI {

    List<Employee> employeeList = Arrays.asList(
            new Employee(101, 18, "张三", 9999.99),
            new Employee(102, 59, "李四", 8888.88),
            new Employee(103, 28, "王五", 7777.77),
            new Employee(104, 88, "赵六", 6666.66),
            new Employee(104, 38, "田七", 5555.55),
            new Employee(104, 38, "田七", 5555.55),
            new Employee(104, 38, "田七", 5555.55)
    );

    @Test
    public void test1(){

        //内部迭代，迭代操作由Stream API完成
        Stream<Employee> employeeStream = employeeList.stream()
                .filter((e) -> {
                    System.out.println("Stream API 的中间操作");
                    return e.getAge() > 35;
                });

        //终止操作：一次性执行全部内容，即"惰性求值"
        employeeStream.forEach(System.out::println);
    }

    //外部迭代
    @Test
    public void test2(){
        Iterator<Employee> iterator = employeeList.iterator();

        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Test
    public void test3(){
        employeeList.stream().filter((e) -> {
            System.out.println("短路");
            return e.getSalary() > 5000;
        })
                .limit(2)
                .forEach(System.out::println);
    }

    @Test
    public void test4(){
        employeeList.stream()
                .filter((e) -> e.getSalary() > 5000)
                .skip(2)
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * 映射
     * map:接受Lambda，将元素转换成其他形式或提取信息。接受一个函数作为参数，该函数会被应用到每个元素上
     * ，并将其映射成一个新的元素；
     * flatMap: 接受一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     */

    @Test
    public void test5(){
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");

        list.stream().map((str) -> str.toUpperCase()).forEach(System.out::println);

        employeeList.stream().map(Employee::getName).forEach(System.out::println);

        System.out.println("=========================================");

        Stream<Stream<Character>> stream = list.stream()
                .map(TestStreamAPI::filterCharacter);

        stream.forEach((sm) -> sm.forEach(System.out::println));

        System.out.println("=========================================");

        Stream<Character> sm = list.stream()
                .flatMap(TestStreamAPI::filterCharacter);
        sm.forEach(System.out::println);
    }


    public static Stream<Character> filterCharacter(String str){
        List<Character> list = new ArrayList<>();

        for(Character character :  str.toCharArray()){
            list.add(character);
        }

        return list.stream();
    }

    /**
     * 排序
     * sorted():自然排序 (Comparable)
     * sorted(Comparator): 定制排序
     */

    @Test
    public void test7(){
        List<String> list = Arrays.asList("ccc", "aaa", "bbb", "ddd", "eee");
        list.stream().sorted().forEach(System.out::println);

        System.out.println("==============================================");

        employeeList.stream()
                .sorted((e1, e2) -> {
                    if(e1.getAge().equals(e2.getAge())){
                        return e1.getName().compareTo(e2.getName());
                    }else{
                        return e1.getAge().compareTo(e2.getAge());
                    }
                }).forEach(System.out::println);
    }
}
