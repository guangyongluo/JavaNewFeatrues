package com.vilin.functionalInterface;

public class Demo02PrintRef {
    private static void printString(Printable data) {
        data.print("Hello, World!");
    }

    public static void main(String[] args) {
        printString(System.out::println);
    }

}
