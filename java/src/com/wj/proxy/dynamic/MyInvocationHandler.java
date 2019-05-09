package com.wj.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyInvocationHandler implements InvocationHandler {

    //被代理的对象
    private Object factory;

    public Object getFactory() {
        return factory;
    }

    public void setFactory(Object factory) {
        this.factory = factory;
    }

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(factory.getClass().getClassLoader(),factory.getClass().getInterfaces(),this);
    }



    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        doSomeThingBefore();
        Object rtn = method.invoke(factory, args);
        doSomeThingEnd();
        return rtn;
    }

    private void doSomeThingEnd() {
        System.out.println("前置服务");
    }

    private void doSomeThingBefore() {
        System.out.println("后置服务");
    }
}
