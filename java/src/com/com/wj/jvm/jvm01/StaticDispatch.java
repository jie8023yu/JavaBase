package com.com.wj.jvm.jvm01;

/**
 * 静态分派
 */
public class StaticDispatch {

    static class Man {}

    static class Human extends Man {}

    static class Woman extends Man {}


    public void sayHello(Man man) {
        System.out.println("man");
    }

    public void sayHello(Human human) {
        System.out.println("human");
    }

    public void sayHello(Woman woman) {
        System.out.println("woman");
    }

    public static void main(String[] args) {
        Man man = new Human();
        Man man2 = new Woman();

        StaticDispatch staticDispatch = new StaticDispatch();
        staticDispatch.sayHello(man);
        staticDispatch.sayHello(man2);
    }

}
