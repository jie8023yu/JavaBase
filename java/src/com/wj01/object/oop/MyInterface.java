package com.wj01.object.oop;

public interface MyInterface {

    /*public static final*/ int MAX_AGE = 10;


    /*public abstract */void test01();
}

class MyClass implements MyInterface {

    @Override
    public void test01() {
        System.out.println(MAX_AGE);
    }
}