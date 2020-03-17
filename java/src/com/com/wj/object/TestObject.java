package com.com.wj.object;

public class TestObject {


    public static void main(String[] args) {
        T t = new T();
        t = new T();
    }
}

class T {

    static {
        System.out.println("static");
    }


    public T() {
        System.out.println("T()");
    }


    {
        System.out.println("{}");
    }

}
