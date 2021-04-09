package com.vilin.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/**
 * 测试lambda表达式总结：
 *
 * 1. 左右遇一括号省
 * 2. 左侧推断类型省
 * 3. 能省则省
 */
public class LambdaTest3 {

    /**
     * 语法格式一、无参数，无返回值
     * () -> System.out.println("Hello Lambda!");
     */
    @Test
    public void test1(){
        int num = 0; // jdk1.7前，必须是final

        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World!" + num);
            }
        };

        new Thread(r1).start();

        System.out.println("=======================");

        Runnable r2 = () -> System.out.println("Hello Lambda!");

        new Thread(r2).start();
    }

    /**
     * 语法格式二：有一个参数，并且无返回值
     */
    @Test
    public void test2(){
        Consumer<String> consumer = (x) -> System.out.println(x);
        consumer.accept("hello lambda...");
    }

    /**
     * 语法格式三：只有一个参数，参数的小括号可以不写
     */
    @Test
    public void test3(){
        Consumer<String> consumer = x -> System.out.println(x);
        consumer.accept("hello lambda...");
    }

    /**
     * 语法格式四：有两个以上的参数，有返回值，并且Lambda体中有多条语句
     */
    @Test
    public void test4(){
        Comparator<Integer> comparator = (x, y) -> {
            System.out.println("functional interface");
            return Integer.compare(x, y);
        };

        System.out.println(comparator.compare(2, 3));
    }

    /**
     * 语法格式五：如果Lambda体中只有一条语句，return和大括号都可以省略不写
     */
    @Test
    public void test5(){
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
        System.out.println(comparator.compare(9, 8));
    }

    /**
     * 语法格式六：Lambda表达式参数列表数据类型可以省略不写，因为JVM编辑器通过上下文推断出数据类型，即"类型推断"
     */
    @Test
    public void test6(){
//        String[] strs;
//        strs = {"aaa", "bbb", "ccc"};

        List<String> list = new ArrayList<>();
    }
}
