package com.com.wj.jvm.jvm01;

public class Stack {

    public Object[] elements;

    private int size = 0;

    private final static int cap = 16;

    public Stack() {
        elements = new Object[16];
    }

    public void push(Object o) {
        elements[size] = o;
        size++;
    }

    public Object pop() {
        size--;
        Object o = elements[size];
        elements[size] = null;
        return o;
    }
}
