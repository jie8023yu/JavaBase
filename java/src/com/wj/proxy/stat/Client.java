package com.wj.proxy.stat;

public class Client {

    public static void main(String[] args) {
        ManToolsFactory factory = new AaFactory();
        Proxy1 proxy = new Proxy1(factory);
        proxy.saleManTools("12");
    }
}
