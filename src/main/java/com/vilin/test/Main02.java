package com.vilin.test;

import java.util.Scanner;

public class Main02 {
    public static void main(String[] args) {
//        Scanner reader = new Scanner(System.in);
//        while(reader.hasNext()){
//            int k = reader.nextInt();
//            int n = reader.nextInt();
//            int m = reader.nextInt();
//        }
        System.out.println(countLuckNum(123,7 ,8));

    }

    public static int countLuckNum(int k, int n, int m){
        if(n >= m){
            return 0;
        }

        int i = 0;
        while(k > (int)Math.pow(m, i)){
            i++;
        }

        int result = 0;
        int temp = 0;

        for(int j = i; j >= 0; j--){
            temp = k / (int) Math.pow(m, j);
            k = k % (int) Math.pow(m, j);
            if (temp == n)
                result++;

        }

        return result;
    }
}
