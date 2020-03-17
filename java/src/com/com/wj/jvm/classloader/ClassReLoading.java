package com.com.wj.jvm.classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * class重新加载
 */
public class ClassReLoading {


    public static class MyLoader extends ClassLoader {

        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {

            File file = new File("I:/test/",name.replaceAll("\\.","/").concat(".class"));
            if (!file.exists()) return super.loadClass(name);
            try {
                InputStream is = new FileInputStream(file);
                byte[] b = new byte[is.available()];
                is.read(b);
                return defineClass(name,b,0,b.length);
            } catch (Exception e) {

            }

            return super.loadClass(name);
        }
    }


    public static void main(String[] args) throws ClassNotFoundException {
//        testReload();
        testNormal();
    }

    public static void testReload() throws ClassNotFoundException {
        ClassLoader loader = new MyLoader();
        Class clz = loader.loadClass("com.com.wj.jvm.classloader.Test2");
        loader = new MyLoader();
        Class clz2 = loader.loadClass("com.com.wj.jvm.classloader.Test2");
        System.out.println(clz == clz2);
    }

    /**
     * 这里跑不出来想要的结果啊，为什么呢？是不是马士兵又讲错了？？？？
     * @throws ClassNotFoundException
     */
    public static void testNormal() throws ClassNotFoundException {
        ClassLoader loader;
        loader = new DefineClassLoader();
        Class clz = loader.loadClass("com.com.wj.jvm.classloader.Test2");
        loader = null;
        System.out.println(clz.hashCode());
        loader = null;
        loader = new DefineClassLoader();
        Class clz2 = loader.loadClass("com.com.wj.jvm.classloader.Test2");
//        loader = null;
        System.out.println(clz2.hashCode());
        System.out.println(clz == clz2);
    }



}
