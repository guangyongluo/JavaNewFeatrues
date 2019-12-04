package com.vilin.functionalInterface;

public class Demo12ArrayInitRef {
    private static int[] initArray(int length, ArrayBuilder builder) {
        return builder.buildArray(length);
    }

    public static void main(String[] args) {
        int[] array = initArray(10, int[]::new);
    }
}
