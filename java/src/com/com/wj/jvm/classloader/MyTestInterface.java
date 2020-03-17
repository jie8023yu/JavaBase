package com.com.wj.jvm.classloader;

import java.util.Random;

/**
 * 当一个接口在初始化的时候，并不要求其父接口都完成了初始化
 * 只有真正使用父接口的时候（如引用接口中所定义的常量时），才会初始化
 */
public class MyTestInterface {

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        System.out.println(Child111.b);
    }
}

interface Parent11 {

    //注意：定义在接口中的变量，默认都是public static final 的，加不加都一样(默认是常量)
    public static final  int a = 5;
}

interface Child111 extends Parent11 {

    public static int b = 6;
    public static int bb = new Random().nextInt(2);
}



