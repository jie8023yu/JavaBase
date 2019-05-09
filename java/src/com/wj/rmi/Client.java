package com.wj.rmi;

import java.rmi.Naming;

public class Client {

    public static void main(String[] args) throws Exception {
        //通过RMI发现服务
        IOrder order = (IOrder) Naming.lookup("rmi://localhost:6666/order");
        System.out.println(order.pay("1234"));
    }
}
