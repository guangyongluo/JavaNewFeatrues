package com.vilin.functionalInterface;

public class Demo03PrintOverload {
    private static void printInteger(PrintableInteger data) {
        data.print(1024);
    }

    public static void main(String[] args) {
        printInteger(System.out::println);
    }
}
