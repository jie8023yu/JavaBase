package com.wj01.object.oop;

public class TestAnonymousInnerClass {

    public static void main(String[] args) {
        TestAnonymousInnerClass.test01(new AA() {
            @Override
            public void aa() {
                System.out.println("test");
            }
        });
    }

    public static void test01(AA aa) {
        aa.aa();
    }
}

interface AA {

    void aa();
}
