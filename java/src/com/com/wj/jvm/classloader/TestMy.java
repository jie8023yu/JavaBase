package com.com.wj.jvm.classloader;

public class TestMy {

    private MyInterface myInterface = new MyInterfaceImpl();

    public TestMy() {
        System.out.println("TestMy()");
    }
}
