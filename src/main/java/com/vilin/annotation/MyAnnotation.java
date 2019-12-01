package com.vilin.annotation;

import com.vilin.enumeration.test.Gender;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

    String value() default "default";
    String[] datas() default {"zhangsan", "lisi", "wangwu"};
    Gender gender() default Gender.MALE;
    AnnotationDemo annotationDemo() default @AnnotationDemo("annotation");
}
