package com.vilin.lambda;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LambdaTest4 {

    List<Employee> employeeList = Arrays.asList(
            new Employee(101, 18, "张三", 9999.99),
            new Employee(102, 59, "李四", 8888.88),
            new Employee(103, 28, "王五", 7777.77),
            new Employee(104, 88, "赵六", 6666.66),
            new Employee(104, 38, "田七", 5555.55)
    );

    @Test
    public void test1() {
        Collections.sort(employeeList, (e1, e2) -> {
            if(e1.getAge() == e2.getAge()){
                return e1.getName().compareTo(e2.getName());
            }else {
                return Integer.compare(e1.getAge(), e2.getAge());
            }
        });

        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }

    @Test
    public void test2(){
        System.out.println(strHandler("\t\t\t hello, world!", (str) -> str.toUpperCase()));
    }

    //需求：用于处理字符串
    public String strHandler(String str, MyFunction<String> mf){
        return mf.getValue(str);
    }
}
