package com.com.wj.jvm.classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 定义类加载器
 * 初始类加载器
 */
public class Test3 {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        DefineClassLoader2 classLoader = new DefineClassLoader2("I:/test");

        DefineClassLoader2 classLoader2 = new DefineClassLoader2("I:/test");

        Class clz = classLoader.loadClass("com.com.wj.jvm.classloader.MyPerson");

        Class clz2 = classLoader2.loadClass("com.com.wj.jvm.classloader.MyPerson");

        /**
         * 当把IDEA编译的class文件删除，让从I:/test加载，此时相当于两个不同的类加载器加载，命名空间不一样
         * 加载两份不同的class文件
         */
        System.out.println(clz == clz2);

        Method method = clz.getMethod("setMyPerson",Object.class);

        method.invoke(clz.newInstance(),clz2.newInstance());
    }
}
