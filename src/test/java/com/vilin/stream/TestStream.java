package com.vilin.stream;

import com.vilin.lambda.Employee;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class TestStream {

    List<Employee> employeeList = Arrays.asList(
            new Employee(101, 18, "张三", 9999.99, Employee.Status.FREE),
            new Employee(102, 59, "李四", 8888.88, Employee.Status.BUSY),
            new Employee(103, 28, "王五", 7777.77, Employee.Status.VOCATION),
            new Employee(104, 88, "赵六", 6666.66, Employee.Status.FREE),
            new Employee(104, 38, "田七", 5555.55, Employee.Status.BUSY)
    );

    @Test
    public void test1(){
        Integer[] nums = new Integer[]{1,2,3,4,5};

        List<Integer> list = Arrays.stream(nums).map((x) -> x * x).collect(Collectors.toList());
        list.forEach(System.out::println);
    }

    @Test
    public void test2(){
        Optional<Integer> sum = employeeList.stream()
                .map((e) -> 1)
                .reduce(Integer::sum);

        System.out.println(sum.get());
    }

    @Test
    public void test3(){
        long sum = LongStream.rangeClosed(0, 100000000L)
                .parallel()
                .reduce(0, Long::sum);
        System.out.println(sum);
    }
}
