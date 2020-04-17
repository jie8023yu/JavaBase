package com.com.wj.jvm.gc;

public class Test {

    public static void main(String[] args) {
        byte b = 127;
        /*
        byte 8位：
            0000 0000

            1 1 1 1  1 1 1 1
            2^7 = 2 * 2 * 2 * 2 * 2 * 2 * 2 = 128
            2^6 = 64
            2^5 = 32
            2^4 = 16
            2^3 = 8
            2^2 = 4
            2^1 = 2
            2^0 =

            2^7 - 1 = 255个数
            -128 - 127
         */
        System.out.println(16);
    }
}
