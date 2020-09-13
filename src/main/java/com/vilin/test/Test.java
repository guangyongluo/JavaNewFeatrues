package com.vilin.test;

public class Test {

    public static double getMaxLen(double X, double Y, double Z){

        if(X <= 0 || Y <= 0 || Z <=0){
            throw new RuntimeException("输入铜棒的长度必须大于0");
        }

        double primeValue = (X + Y + Z) / 1000;

        while(true){
            if(X / primeValue + Y / primeValue + Z / primeValue > 1000){
                primeValue =+ Double.MIN_VALUE;
            } else if(X / primeValue + Y / primeValue + Z / primeValue < 1000){
                primeValue =- Double.MIN_VALUE;
            }
            return primeValue;
        }
    }

    public static void main(String[] args) {
        System.out.println(getMaxLen(1.0, 2.0, 3.0));
    }
}
