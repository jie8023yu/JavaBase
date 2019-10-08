package com.com.wj.jvm.jvm01;

public class Test2 {


    public static void main(String[] args) {
        Test2 test2  = new Test2();
        System.out.println(test2.cal());
    }


    public int cal() {
        int a=100;
        int b=200;
        int c=300;
        return(a+b)*c;
    }
}
