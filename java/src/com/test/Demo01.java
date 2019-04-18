package com.test;

public class Demo01 extends Demo2{

    static {
        System.out.println("static 块");
        System.out.println(Demo2.num);
    }

    public Demo01() {
        System.out.println("Demo1 构造器");
    }

    public static void get() {
        System.out.println("get()");
    }


   /* public static void main(String[] args) {
        System.out.println(1);
    }*/


}

class Demo2 {


    public static int num = 10;

    static {
        System.out.println("static Demo02 块");
        System.out.println("static Demo02 num = " + num);
    }

    public Demo2() {
        System.out.println("Demo2 构造器");
    }
}
