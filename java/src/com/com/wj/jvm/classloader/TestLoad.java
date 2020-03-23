package com.com.wj.jvm.classloader;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestLoad {


    public TestLoad() {
        try {
            DriverManager.getConnection("jdbc:mysql://localhost:3308/test","root","root");
            System.out.println(TestLoad.class.getClassLoader());
            System.out.println(DriverManager.class.getClassLoader());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
