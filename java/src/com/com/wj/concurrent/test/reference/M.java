package com.com.wj.concurrent.test.reference;

public class M {

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize");
    }
}
