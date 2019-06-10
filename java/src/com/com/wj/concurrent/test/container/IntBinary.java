package com.com.wj.concurrent.test.container;

/**
 * 位运算
 */
public class IntBinary {

    public static void main(String[] args) {
        // & 位与 同1位1，同0为0，不同为0  位非~   位异或 ^  不同为1，相同为0  位或 |
        System.out.println("the 4 is " + Integer.toBinaryString(4));
        System.out.println("the 6 is " + Integer.toBinaryString(6));
        System.out.println("the 4 & 6 is " + Integer.toBinaryString(4 & 6));
        // 有符号左移 << 第31位用0表示正数，1表示负数 有符号右移 >>  无符号右移 >>>
        // 取模操作 a % (2 ^ n) 等价于 a & (2 ^ n - 1)
    }
}
