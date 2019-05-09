package com.wj.proxy.stat;

import java.lang.reflect.Proxy;

/**
 * 代理对象(静态代理模式)
 */
public class Proxy1 implements ManToolsFactory{

    //真实对象
    public ManToolsFactory realObject;

    public Proxy1(ManToolsFactory realObject) {
        this.realObject = realObject;
    }

    @Override
    public void saleManTools(String size) {
        doSomeThingBefore();
        realObject.saleManTools(size);
        doSomeThingEnd();
    }

    private void doSomeThingEnd() {
        System.out.println("前置服务");
    }

    private void doSomeThingBefore() {
        System.out.println("后置服务");
    }
}
