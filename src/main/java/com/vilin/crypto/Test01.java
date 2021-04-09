package com.vilin.crypto;

import java.lang.reflect.Modifier;

public class Test01 {

    public static void main(String[] args) {
        Test01 test = new Test01();
        Class cls = test.getClass();

        // returns the Java language modifiers for this class
        int i = cls.getModifiers();
        String retval = Modifier.toString(i);
        System.out.println("Class Modifier =" +  retval);

    }
}

enum Day {
    MONDAY, TUESDAY, WEDNESDAY,
    THURSDAY, FRIDAY, SATURDAY, SUNDAY
}
