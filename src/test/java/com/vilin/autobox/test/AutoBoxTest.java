package com.vilin.autobox.test;

import org.junit.jupiter.api.Test;

public class AutoBoxTest {

    @Test
    public void test1(){

    }

    @Test
    public void test2(){
        Integer i = 200;
        Integer j = 200;
        System.out.println(i == j);
        System.out.println(i.equals(j)); //true
    }

    @Test
    public void test3(){
        String s = "123";

//        Integer result = new Integer(s);
//        Integer result = Integer.valueOf(s);
        Integer result = Integer.parseInt(s);

        System.out.println(result);
    }
}
