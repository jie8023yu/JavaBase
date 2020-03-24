package com.com.wj.jvm.bytecode;

/**
 * javap -verbose -p 加-p参数才会把私有的方法打印出来
 */
public class MyTest2 {

    String str = "welcome";

    private int x = 5;

    public static Integer it = 2;

    public void setX(int x) {
        this.x = x;
    }

    public static void main(String[] args) {
        MyTest2 myTest2 = new MyTest2();
        myTest2.setX(1);
    }

    private synchronized int getX() {
        return this.x;
    }
}
