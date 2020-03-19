package com.com.wj.jvm.classloader;

import sun.misc.Launcher;

public class ClassLoaderScope {

    public static void main(String[] args) {
        String pathBoot = System.getProperty("sun.boot.class.path");

        System.out.println(pathBoot.replaceAll(";",System.lineSeparator()));

        System.out.println("----------------");
        String pathExt = System.getProperty("java.ext.dirs");
        System.out.println(pathExt.replaceAll(";",System.lineSeparator()));

        System.out.println("----------------");
        String pathApp = System.getProperty("java.class.path");
        System.out.println(pathApp.replaceAll(";",System.lineSeparator()));


        System.out.println("--------->>>>>>>>");
        System.out.println(ClassLoader.class.getClassLoader());
        System.out.println(Launcher.class.getClassLoader());
        System.out.println("-------------------------");
        System.out.println(System.getProperty("java.system.class.loader"));

        //-Djava.system.class.loader = com.com.wj.jvm.classloader.DefineClassLoader2
        //自定义系统类加载器
        System.out.println(ClassLoaderScope.class.getClassLoader());
        System.out.println(DefineClassLoader2.class.getClassLoader());
        //获取系统类加载器
        System.out.println(ClassLoader.getSystemClassLoader());

    }
}
