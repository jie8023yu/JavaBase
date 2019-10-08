package com.com.wj.jvm.jvm01;

/**
 * 从字节码的角度分析为哈打印的是false
 */
public class Exercise {

    static void print(Exercise e1,Exercise e2) {
        System.out.println(e2);
        System.out.println(e1 == (e1 = e2));
        System.out.println(e1);
    }

    public static void main(String[] args) {
        Exercise e1 = new Exercise();
        Exercise e2 = new Exercise();

        print(e1,e2);
    }
}
