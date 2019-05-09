package com.wj.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class OrderImpl extends UnicastRemoteObject implements IOrder {


    protected OrderImpl() throws RemoteException {
    }

    @Override
    public String pay(String id) throws RemoteException {
        return "支付成功：" + id;
    }
}
