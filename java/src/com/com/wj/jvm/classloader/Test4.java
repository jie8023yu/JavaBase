package com.com.wj.jvm.classloader;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test4 {

    public static void main(String[] args) throws ClassNotFoundException {
//        Driver driver = new MyDriver();
//        System.out.println(Driver.class.getClassLoader());
//        Driver.class.getClassLoader().loadClass("com.com.wj.jvm.classloader.MyDriver");
//        System.out.println(MyDriver.class.getClassLoader());

//        Class.forName("")
       /* try {
            DriverManager.getConnection("");
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

       DefineClassLoader2 defineClassLoader2 = new DefineClassLoader2("I:/test");
       DefineClassLoader2 defineClassLoader21 = new DefineClassLoader2(defineClassLoader2,"I:/test2");
       Class clz = defineClassLoader2.loadClass("com.com.wj.jvm.classloader.TestMy");
        try {
            System.out.println(clz.getClassLoader());
            clz.newInstance();
            System.out.println(MyInterface.class.getClassLoader());
            System.out.println(MyInterfaceImpl.class.getClassLoader());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("1");
    }

}
