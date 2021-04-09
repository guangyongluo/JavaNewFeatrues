package com.vilin.test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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

    public static int solution1(int N) {

        if(N >= 0) {
            String str = Integer.toString(N);
            int i = 0;
            while(i < str.length()){
                int temp = Integer.parseInt(str.charAt(i)+"");
                if(temp > 5) {
                    i++;
                }else{
                    break;
                }
            }
            StringBuffer sb = new StringBuffer(str);
            return Integer.parseInt(sb.insert(i, '5').toString());
        }else{
            String str = Integer.toString(N);
            int i = 1;
            while(i < str.length()){
                int temp = Integer.parseInt(str.charAt(i)+"");
                if(temp <= 5) {
                    i++;
                }else{
                    break;
                }
            }
            StringBuffer sb = new StringBuffer(str);
            return Integer.parseInt(sb.insert(i, '5').toString());
        }
    }

    public int solution2(int[] A) {

        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();

        for(int i = 0; i < A.length; i++){
            if(!map.containsKey(A[i])){
                map.put(A[i], 0);
            }
            map.put(A[i], map.get(A[i])+1);
        }

        Map.Entry temp = null;

        for(Map.Entry entry : map.entrySet()){
            if(entry.getKey() == entry.getValue()){
                temp = entry;
            }
        }

        if(temp == null){
            return 0;
        }else {
            return (Integer) temp.getValue();
        }
    }

    public static void main(String[] args) {
//        System.out.println(getMaxLen(1.0, 2.0, 3.0));
        System.out.println(solution1(5000));
    }
}
