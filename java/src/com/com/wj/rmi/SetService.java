/*
package com.com.wj.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class SetService {

    public static void main(String[] args) {
        try {
            StudentService studentService = new StudentServiceImpl();
            LocateRegistry.createRegistry(5008);
            Naming.bind("rmi://127.0.0.1:5008/StudentService",studentService);
            System.out.println("服务已经启动");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
*/
