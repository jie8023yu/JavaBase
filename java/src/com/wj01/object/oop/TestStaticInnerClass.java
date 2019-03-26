package com.wj01.object.oop;

/**
 * 测试静态内部类
 */
public class TestStaticInnerClass {

    public static void main(String[] args) {
        //创建内部类对象
        Outer2.Inner2 inner = new Outer2.Inner2();
        
    }
}

class Outer2 {

    static class Inner2 {

    }
}
