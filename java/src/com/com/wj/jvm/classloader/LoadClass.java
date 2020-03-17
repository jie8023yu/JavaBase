package com.com.wj.jvm.classloader;

public class LoadClass {

    public static void main(String[] args) throws ClassNotFoundException {
        Class clz = LoadClass.class.getClassLoader().loadClass("com.com.wj.jvm.classloader.Test");
        System.out.println(clz.getName());
    }
}
