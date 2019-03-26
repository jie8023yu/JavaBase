package com.wj04.collection;

import java.util.Arrays;

/**
 * 自定义实现一个ArrayList，体验ArrayList的底层原理
 * 第三版：加入扩容
 */
public class MyArrayList03<E> {

    private Object[] elementData;
    private int size;

    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList03() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList03(int capacity) {
        this.elementData = new Object[capacity];
    }

    public void add(E e) {
        //什么时间扩容，不够用的时候扩容
        if(size == elementData.length) {
            //这种拷贝方式在JDK1.8源码中发现不是这样写的
           /* Object[] newArray = new Object[elementData.length + elementData.length >> 1];
            System.arraycopy(elementData,0,newArray,0,elementData.length);*/
           int newCapacity =  elementData.length + (elementData.length >> 1);  //注意：此处运算符的优先级问题，必须加上（）
           elementData = Arrays.copyOf(elementData,newCapacity);
        }
        elementData[size++] = e;
    }


    public static void main(String[] args) {
        MyArrayList03 list = new MyArrayList03();
        for(int i = 0;i < 15;i++) {
            list.add(i + "");
        }
        System.out.println(list);

    }


    @Override
    public String toString() {
        return Arrays.toString(elementData);
    }
}
