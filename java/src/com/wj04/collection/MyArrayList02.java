package com.wj04.collection;

import java.util.Arrays;

/**
 * 自定义实现一个ArrayList，体验ArrayList的底层原理
 * 第二版：加入泛型
 */
public class MyArrayList02<E> {

    private Object[] elementData;
    private int size;

    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList02() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList02(int capacity) {
        this.elementData = new Object[capacity];
    }

    public void add(E e) {
        elementData[size++] = e;
    }


    public static void main(String[] args) {
        MyArrayList02 list = new MyArrayList02();
        list.add("1");
        System.out.println(list);

    }


    @Override
    public String toString() {
        return Arrays.toString(elementData);
    }
}
