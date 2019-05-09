package com.wj.proxy.dynamic;

public class Client {

    public static void main(String[] args) {
        ToolsFactory aFactory = new AFactory();
        ToolsFactory bFactory = new BFactory();
        MyInvocationHandler handler = new MyInvocationHandler();
        handler.setFactory(aFactory);
        ToolsFactory tf1 = (ToolsFactory) handler.getProxyInstance();
        tf1.saleManTools("123");

        handler.setFactory(bFactory);
        tf1.saleManTools("123");

    }
}
