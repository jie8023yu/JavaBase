package com.wj04.collection;

import java.util.Arrays;

/**
 * 自定义实现一个ArrayList，体验ArrayList的底层原理
 * 第一版：简单
 */
public class MyArrayList01 {

    private Object[] elementData;
    private int size;

    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList01() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList01(int capacity) {
        this.elementData = new Object[capacity];
    }

    public void add(Object obj) {
        elementData[size++] = obj;
    }


    public static void main(String[] args) {
        MyArrayList01 list = new MyArrayList01();
        list.add("1");
        System.out.println(list);

    }


    @Override
    public String toString() {
        return Arrays.toString(elementData);
    }
}
