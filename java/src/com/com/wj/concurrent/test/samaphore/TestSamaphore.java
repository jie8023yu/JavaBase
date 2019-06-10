package com.com.wj.concurrent.test.samaphore;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * 信号量
 * 控制同时访问某个特定资源的线程数量
 */
public class TestSamaphore {

    private final static int POOL_SIZE = 10;
    private final Semaphore useful,useless;

    public TestSamaphore() {
        this.useful = new Semaphore(POOL_SIZE);
        this.useless = new Semaphore(0);
    }

    public TestSamaphore(Semaphore useful, Semaphore useless) {
        this.useful = useful;
        this.useless = useless;
    }

    private static LinkedList<Connection> pool = new LinkedList<>();
    static {
        for (int i = 0; i < POOL_SIZE; i++) pool.addLast(null);
    }


    public void test() {
        /*Proxy.newProxyInstance(TestSamaphore.class.getClassLoader(), Connection.class.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return method.invoke(new Connection() {
                }, args);
            }
        });*/
    }

}

