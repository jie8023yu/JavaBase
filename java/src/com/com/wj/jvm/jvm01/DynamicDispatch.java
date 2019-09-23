package com.com.wj.jvm.jvm01;

/**
 * 动态分派
 */
public class DynamicDispatch {


    public static void main(String[] args) {
        FatherClass cls = new SubClass();
        cls.sayHello();
    }
}

class SubClass extends FatherClass {

    @Override
    public void sayHello() {
        System.out.println("sub");
    }
}

class FatherClass {

    public void sayHello() {
        System.out.println("father");
    }

}
