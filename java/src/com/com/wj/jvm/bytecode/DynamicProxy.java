package com.com.wj.jvm.bytecode;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 系统属性，设置这个sun.misc.ProxyGenerator.saveGeneratedFiles后，会把动态代理生成的类对象文件写入本地磁盘
 *
 */
public class DynamicProxy implements InvocationHandler {

    private Object obj;

    public DynamicProxy(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before calling:" + method);
        method.invoke(this.obj,args);
        System.out.println("after calling:" + method);
        return null;
    }

    public static void main(String[] args) {
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        RealClass realClass = new RealClass();
        InvocationHandler handler = new DynamicProxy(realClass);
        Class clz = realClass.getClass();
        MyInterface my = (MyInterface) Proxy.newProxyInstance(clz.getClassLoader(),clz.getInterfaces(),handler);
        my.request();
        System.out.println("-----------------------");
        System.out.println(my.getClass());
        System.out.println(my.getClass().getSuperclass());

    }
}


