package com.com.wj.concurrent.test.reference;

import java.io.IOException;

/**
 * 没有引用指向它的时候
 */
public class T01_NormalReference {

    public static void main(String[] args) throws IOException {
        M m = new M();
        m = null;
        System.gc();

        System.in.read();
    }
}
