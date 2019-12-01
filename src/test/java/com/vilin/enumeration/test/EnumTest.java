package com.vilin.enumeration.test;

import org.junit.jupiter.api.Test;

import java.util.*;

public class EnumTest {

    @Test
    public void test1(){
        Gender gender = Gender.MALE;
        System.out.println(gender.nextGender().ordinal());
        System.out.println(gender.nextGender().name());
        gender.nextGender().fun();
    }

    @Test
    public void test2(){
        Vector vector = new Vector();
        vector.add("IBM");
        vector.add("SUN");
        vector.add("Oracle");
        vector.add("Google");
        vector.add("Apple");

        Enumeration elements = vector.elements();

        while(elements.hasMoreElements()){
            Object element = elements.nextElement();
            System.out.println(element);
        }
    }

    @Test
    public void test3(){
        EnumMap<Gender, String> enumMap = new EnumMap<Gender, String>(Gender.class);
        enumMap.put(Gender.MALE, "男");
        enumMap.put(Gender.FEMAEL, "女");
        for (Iterator<Map.Entry<Gender, String>> iterator = enumMap.entrySet().iterator(); iterator.hasNext();){
            Map.Entry<Gender, String> entry = iterator.next();
            System.out.println(entry.getKey() + "--------" + entry.getValue());
        }
    }
}
