package com.vilin.generic.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GenericTest {

    public void test1(){
        //加上<String>：意味着list里只能存放String
        List list = new ArrayList();
        list.add("zhangsan");
        list.add(123);
        list.add(new Date());

        for(int i = 0; i < list.size(); i++){
            String result = (String)list.get(i);
            System.out.println(result);
        }
    }

    public void test2(){
        //上限
        List<? extends Number> list1 = new ArrayList<Integer>();
        List<? extends Number> list2 = new ArrayList<Long>();

        //上限不确定存放元素的类型，此时调用方法时，如果这个方法的参数使用了泛型类型参数就会报错
//        list1.add(1);
        System.out.println(list1.size());

        //下限
        List<? super Integer> list3 = new ArrayList<Integer>();
        List<? super Integer> list4= new ArrayList<Number>();
        List<? super Integer> list5= new ArrayList<Object>();

        list3.add(1);
    }
}
