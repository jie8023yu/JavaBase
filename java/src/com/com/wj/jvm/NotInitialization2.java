package com.com.wj.jvm;

/**
 * 通过数组来定义引用，不会触发此类的初始化
 */
public class NotInitialization2 {

    public static void main(String[] args) {
        SuperClass[] sca = new SuperClass[10];
    }

}
