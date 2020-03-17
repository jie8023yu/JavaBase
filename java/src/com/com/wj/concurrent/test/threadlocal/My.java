package com.com.wj.concurrent.test.threadlocal;

public class My extends ThreadLocal {

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("My finalize execute...");
    }
}
