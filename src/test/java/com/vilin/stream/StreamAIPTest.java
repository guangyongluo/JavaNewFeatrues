package com.vilin.stream;

import com.vilin.lambda.Employee;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

//Stream API终止操作
public class StreamAIPTest {

    List<Employee> employeeList = Arrays.asList(
            new Employee(101, 18, "张三", 9999.99, Employee.Status.FREE),
            new Employee(102, 59, "李四", 8888.88, Employee.Status.BUSY),
            new Employee(103, 28, "王五", 7777.77, Employee.Status.VOCATION),
            new Employee(104, 88, "赵六", 6666.66, Employee.Status.FREE),
            new Employee(104, 38, "田七", 5555.55, Employee.Status.BUSY)
    );

    /**
     * 查询与匹配
     * allMatch:检查是否匹配所有元素；
     * anyMatch:检查是否至少匹配一个元素；
     * noneMatch:检查是否没有匹配所有元素；
     * findFirst:返回当前流中的第一个元素；
     * findAny:返回当前流中的任意元素；
     * count:返回当前流中的元素总数；
     * max:返回当前流中最大值；
     * min:返回当前流中最小值
     */

    @Test
    public void test1(){
        boolean b1 = employeeList.stream().allMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b1);

        boolean b2 = employeeList.stream().anyMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b2);

        boolean b3 = employeeList.stream().noneMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b3);

        Optional<Employee> optional = employeeList.stream()
                .sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
                .findFirst();

        System.out.println(optional.get());

        Optional<Employee> any = employeeList.stream().filter((e) -> e.getStatus().equals(Employee.Status.FREE)).findAny();

        System.out.println(any.get());

    }

    @Test
    public void test2(){

        Long count = employeeList.stream().count();
        System.out.println("当前公司有" + count + "个人");

        Optional<Employee> max = employeeList.stream()
                .max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(max.get());

        Optional<Double> min = employeeList.stream().map(Employee::getSalary).min(Double::compare);
        System.out.println(min.get());

    }

    //归约：reduce(T identity, BinaryOperator) / reduce(BinaryOperator) 可以将流中元素反复结合起来
    // ，得到一个值
    @Test
    public void test3(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        Integer sum = list.stream().reduce(0, Integer::sum);

        System.out.println("sum = " + sum);

        System.out.println("====================================");

        Optional<Double> optional = employeeList.stream().map(Employee::getSalary).reduce(Double::sum);

        System.out.println("salary total : " + optional.get());

    }

    //收集 collect将流转换为其他形式。接受一个Collector接口的实现，用于给Stream中元素做汇总的方法
    @Test
    public void test4(){
        List<String> list = employeeList.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        list.forEach(System.out::println);

        System.out.println("====================================");

        HashSet<String> hs = employeeList.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));

        hs.forEach(System.out::println);
    }

    @Test
    public void test5(){
        Long count = employeeList.stream()
                .collect(Collectors.counting());
        System.out.println(count);

        System.out.println("===================================");

        Double avg = employeeList.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println("平均工资：" + avg);

        System.out.println("===================================");

        Double sum = employeeList.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));

        System.out.println("工资总额：" + sum);

        System.out.println("====================================");

        Optional<Employee> max = employeeList.stream()
                .collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));

        System.out.println("最高工资员工：" + max.get());

        System.out.println("====================================");

        Optional<Double> min = employeeList.stream()
                .map(Employee::getSalary)
                .collect(Collectors.minBy(Double::compare));

        System.out.println("最低工资：" + min.get());

    }

    //分组
    @Test
    public void test6(){
        Map<Employee.Status, List<Employee>> map = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));

        System.out.println(map);
    }

    //多级分组
    @Test
    public void test7(){
        Map<Employee.Status, Map<String, List<Employee>>> map = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
                    if (e.getAge() <= 35) {
                        return "青年";
                    } else if (e.getAge() <= 50) {
                        return "中年";
                    } else {
                        return "老年";
                    }
                })));

        System.out.println(map);
    }

    //分区
    @Test
    public void test8(){
        Map<Boolean, List<Employee>> map = employeeList.stream()
                .collect(Collectors.partitioningBy((e) -> e.getSalary() > 8000));
        System.out.println(map);
    }

    @Test
    public void test9(){
        DoubleSummaryStatistics st = employeeList.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));

        System.out.println(st.getSum());
        System.out.println(st.getAverage());
        System.out.println(st.getMax());
    }

    @Test
    public void test10(){
        String collect = employeeList.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(",", "[","]"));
        System.out.println(collect);
    }
}
