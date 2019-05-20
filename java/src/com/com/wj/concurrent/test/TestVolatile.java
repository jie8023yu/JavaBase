package com.com.wj.concurrent.test;

import java.util.ArrayList;
import java.util.List;

public class TestVolatile {

    private volatile  int i = 0;

    public void increment() {
        i = i + 1;
    }

    public int get() {
        return i;
    }

    public static void main(String[] args) throws InterruptedException {
        final TestVolatile test = new TestVolatile();
        List<Thread> ts = new ArrayList();
        for (int i = 0; i < 10; i++) {
            ts.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        test.increment();
                    }
                }
            },"t" + i));
        }
        for (Thread thread : ts) thread.start();
        for (Thread thread : ts) thread.join();

        System.out.println(test.get());
    }
}
