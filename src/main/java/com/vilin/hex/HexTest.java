package com.vilin.hex;

import java.math.BigInteger;

public class HexTest {

    private final static int LAST_N_BIT = 4;

    /**
     * 将 4字节的16进制字符串，转换为32位带符号的十进制浮点型
     * @param str 4字节 16进制字符
     * @return
     */
    public static float hexToFloat(String str){
        return  Float.intBitsToFloat(new BigInteger(str, 16).intValue());
    }


    /**
     * 将带符号的32位浮点数装换为16进制
     * @param value
     * @return
     */
    public static String folatToHexString(Float value){
        return  Integer.toHexString(Float.floatToIntBits(value));
    }

    /**
     * 将任意整数截取后4位不够补零
     * @param n 任意整数，负数抛异常
     * @return
     */
    public static String intToString(int n, int m){
        if(n < 0){
            throw new RuntimeException("参数不能小于0");
        }

        String temp = Integer.toString(n);
        if(temp.length() > m){
            return temp.substring(temp.length() - m);
        }else {
            StringBuffer result = new StringBuffer(temp);
            int len = m - temp.length();
            for(int i = len; i > 0; i--){
                result.insert(0,"0");
            }
            return result.toString();
        }
    }

    public static void main(String[] args) {
//        String str = "A.C";
//        System.out.println(HexTest.hexToFloat(str.trim()));

        int intTest = 123456;
        System.out.println(HexTest.intToString(intTest, 4));
    }
}
