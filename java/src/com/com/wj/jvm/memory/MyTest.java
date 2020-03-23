package com.com.wj.jvm.memory;

public class MyTest {

    public static void main(String[] args) {
        for (;;){
            System.out.println("hello world");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
