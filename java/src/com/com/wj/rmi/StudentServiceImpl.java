package com.com.wj.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl extends UnicastRemoteObject implements StudentService{

    protected StudentServiceImpl() throws RemoteException {
    }

    @Override
    public List<Student> getList() throws RemoteException {
        List<Student> list = new ArrayList<>();
        Student s1 = new Student();
        s1.setName("张三");
        s1.setAge(15);
        list.add(s1);
        Student s2 = new Student();
        s1.setName("李四");
        s1.setAge(16);
        list.add(s2);
        return list;
    }
}
