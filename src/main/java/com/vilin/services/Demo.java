package com.vilin.services;

import com.vilin.annotation.AnnotationDemo;
import com.vilin.annotation.MyAnnotation;
import com.vilin.enumeration.test.Gender;

@MyAnnotation(
        value = "张三",
        datas = {"abc", "123"},
        gender = Gender.FEMAEL,
        annotationDemo = @AnnotationDemo(value = "XXX")
)
public class Demo {

    @MyAnnotation
    public String info;

    @MyAnnotation
    public void fun(){

    }
}
