package com.wj01.object.oop;

public class TestInnerClass {

    public static void main(String[] args) {
        Outer outer = new Outer();

        //创建内部类对象
        Outer.inner inner = new Outer().new inner();
        inner.isShow();
    }
}

class Outer {

    private int age = 10;

    public void testOuter() {

    }

    class inner {
        int age = 20;
        public void isShow() {
            System.out.println(Outer.this.age);
            System.out.println(this.age);
        }
    }

}
