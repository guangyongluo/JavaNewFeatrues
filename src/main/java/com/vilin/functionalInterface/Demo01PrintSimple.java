package com.vilin.functionalInterface;

public class Demo01PrintSimple {
    private static void printString(Printable data) {
        data.print("Hello, World!");
    }

    public static void main(String[] args) {
        printString(s -> System.out.println(s));
    }

}
