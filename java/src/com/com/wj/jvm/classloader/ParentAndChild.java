package com.com.wj.jvm.classloader;

public class ParentAndChild {

    public static void main(String[] args) {
        System.out.println(ParentAndChild.class.getClassLoader());
        System.out.println(ParentAndChild.class.getClassLoader().getClass().getClassLoader());
        System.out.println(ParentAndChild.class.getClassLoader().getParent());
        System.out.println(ParentAndChild.class.getClassLoader().getParent().getParent());
    }
}
