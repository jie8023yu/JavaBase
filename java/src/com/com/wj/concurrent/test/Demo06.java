package com.com.wj.concurrent.test;

public class Demo06 {

    public static void main(String[] args) {
        System.out.println("开始中断");
        Thread.currentThread().interrupt();
        System.out.println(1);
    }

}
