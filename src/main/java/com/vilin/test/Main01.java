package com.vilin.test;

public class Main01 {
    public static void main(String args[]){
        int result = gcd(5, 7);
        System.out.println(result);
    }

    public static int gcd (int a,int b){
        int temp;
        if(a < b){
            temp = a;
            a = b;
            b = temp;
        }
        int m = a;
        int n = b;
        while(b != 0){
            int c = a % b;
            a = b;
            b = c;
        }

        return m*n/a;
    }

}
