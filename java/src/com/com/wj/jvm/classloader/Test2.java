package com.com.wj.jvm.classloader;

/**
 * 关于命名空间（子加载器加载的类+父加载器加载的类构成的）
 * 1.子加载器所加载的类能访问父加载器加载的类
 * 2.父加载器所加载的类不能访问子加载器加载的类
 *
 */
public class Test2 {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        DefineClassLoader2 classLoader = new DefineClassLoader2("I:/test");
        Class clz = classLoader.loadClass("com.com.wj.jvm.classloader.MySample");
        System.out.println(clz.getClassLoader());
        Object obj = clz.newInstance();
        System.out.println(obj.getClass().getClassLoader());
    }
}
