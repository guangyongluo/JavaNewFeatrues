package com.vilin.date;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class LocalDateTest {

    @Test
    public void test1(){
        LocalDate localDate = LocalDate.of(2019, 12, 05);
        System.out.println(localDate);
        localDate = LocalDate.now();
        System.out.println(localDate);

    }
}
