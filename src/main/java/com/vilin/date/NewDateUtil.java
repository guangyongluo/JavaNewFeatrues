package com.vilin.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class NewDateUtil {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);
        date.setYear(date.getYear() + 1);
        System.out.println(date);

        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        LocalDate newLocalDate = localDate.plusYears(1);
        System.out.println(localDate);
        System.out.println(newLocalDate);

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        LocalDateTime newLocalDateTime = localDateTime.plusDays(1);
        System.out.println(localDateTime);
        System.out.println(newLocalDateTime);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        System.out.println(newLocalDateTime.format(dateTimeFormatter));
    }
}
