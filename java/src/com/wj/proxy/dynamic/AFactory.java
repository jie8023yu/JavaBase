package com.wj.proxy.dynamic;

public class AFactory implements ToolsFactory{


    @Override
    public void saleManTools(String size) {
        System.out.println("woman factory");
    }
}
