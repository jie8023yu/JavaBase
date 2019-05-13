package com.com.wj.jvm;

public class NotInitialization {

    public static void main(String[] args) {
        /**
         * 这样写只会去加载SuperClass，而不会去加载SubClass
         * 只有直接定义这个字段的类才会初始化，因此通过子类来引用父类中的定义的静态字段
         * 只会触发父类的初始化而不会触发子类的初始化
         */
        System.out.println(SubClass.value);
    }
}

class SuperClass {

    static {
        System.out.println("SuperClass init!");
    }

    public static int value = 123;
}

class SubClass extends SuperClass {

    static {
        System.out.println("SubClass init!");
    }

    public static int vlaue = 123;
}
