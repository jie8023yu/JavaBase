package com.wj.rpc.client;

import com.wj.rpc.server.TechInterface;

public class Client {

    public static void main(String[] args) {
        try {
            TechInterface techInterface = RpcClientFrame.getRemoteProxyObject(TechInterface.class);
            System.out.println(techInterface.xj("hello world"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
