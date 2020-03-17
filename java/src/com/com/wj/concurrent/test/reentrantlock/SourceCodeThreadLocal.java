package com.com.wj.concurrent.test.reentrantlock;

public class SourceCodeThreadLocal {

    public static void main(String[] args) {
        ThreadLocal threadLocal = new ThreadLocal();

        threadLocal.set("abc");
    }
}
