package com.wj.proxy.stat;

public class AaFactory implements ManToolsFactory{

    @Override
    public void saleManTools(String size) {
        System.out.println("size为：" + size);
    }
}
