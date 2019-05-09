package com.wj.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * 服务端
 */
public class Server {

    public static void main(String[] args) throws Exception{
        IOrder order = new OrderImpl();
        //本地的服务注册到一个端口中
        LocateRegistry.createRegistry(6666);
        Naming.bind("rmi://localhost:6666/order",order);
        System.out.println("服务器启动");
    }
}
