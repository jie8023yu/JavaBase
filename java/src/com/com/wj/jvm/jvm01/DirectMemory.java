package com.com.wj.jvm.jvm01;

import java.nio.ByteBuffer;

/**
 * 直接内存
 *
 * -XX:MaxDirectMemorySize=10M
 *
 */
public class DirectMemory {

    public static void main(String[] args) {
        /*
        * Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
        *
        * 限制直接内存
        * */

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * 1024 * 14);
    }
}
