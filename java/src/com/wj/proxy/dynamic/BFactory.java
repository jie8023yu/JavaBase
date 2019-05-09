package com.wj.proxy.dynamic;

public class BFactory implements ToolsFactory{

    @Override
    public void saleManTools(String size) {
        System.out.println("man tools");
    }
}
