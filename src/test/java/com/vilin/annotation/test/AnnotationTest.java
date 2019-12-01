package com.vilin.annotation.test;

import com.vilin.annotation.MyAnnotation;
import com.vilin.services.Demo;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class AnnotationTest {

    @Test
    public void test(){
        MyAnnotation myAnnotation;
        if(Demo.class.isAnnotationPresent(MyAnnotation.class)){
            myAnnotation = Demo.class.getDeclaredAnnotation(MyAnnotation.class);
            System.out.println(myAnnotation.value());
            System.out.println(Arrays.toString(myAnnotation.datas()));
            System.out.println(myAnnotation.gender().name());
            System.out.println(myAnnotation.annotationDemo().value());
        }
    }
}
