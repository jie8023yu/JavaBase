package com.wj04.collection;

import java.util.Arrays;

/**
 * 自定义实现一个ArrayList，体验ArrayList的底层原理
 * 第五版：remove方法
 */
public class MyArrayList05<E> {

    private Object[] elementData;
    private int size;

    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList05() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList05(int capacity) {
        if(capacity < 0) {
            throw new RuntimeException("容器空间不能小于0");
        }else if(capacity == 0){
            this.elementData = new Object[DEFAULT_CAPACITY];
        }else{
            this.elementData = new Object[capacity];
        }

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

    public E get(int index){
        checkIndex(index);
        return (E) elementData[index];
    }

    public void set(int index,E element){
        checkIndex(index);
        elementData[index] = element;
    }

    public boolean remove(E e){
        if(e == null) {
            for(int i = 0;i < size;i++){
                if(elementData[i] == null){
                    fastMove(i);
                    return true;
                }
            }
        }else{
            for(int i = 0;i < size;i++) {
                if(e.equals(elementData[i])){
                    fastMove(i);
                    return true;
                }
            }
        }
        return false;

    }

    public E remove(int index){
        checkIndex(index);
        E oldValue = (E) elementData[index];
        fastMove(index);
        return oldValue;
    }


    private void fastMove(int index) {
        int numMoved = size - index - 1;
        if(numMoved > 0) System.arraycopy(elementData,index + 1,elementData,index,numMoved);
        elementData[--size] = null;
    }



    public void checkIndex(int index) {
        if(index < 0 || index > size) {
            throw new RuntimeException("索引不合法:" + index);
        }
    }


    public static void main(String[] args) {
        MyArrayList05 list = new MyArrayList05();
        for(int i = 0;i < 15;i++) {
            list.add(i + "");
        }
        System.out.println(list);
        System.out.println(list.get(10));
        list.set(2,"测试");
        System.out.println(list);
        list.remove("1");
        System.out.println(list);
        list.remove(1);
        System.out.println(list);
    }


    @Override
    public String toString() {
        return Arrays.toString(elementData);
    }
}
