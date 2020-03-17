package com.com.wj.concurrent.test.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.List;

/**
 * 虚引用(基本没用)
 * 虚引用get值get不到，永远为空
 * 只有当被清理的时候会通知到队列里面
 *
 * NIO使用，直接内存
 *
 * 当DirectByteBuffer为空时，Queue接收到时，去清除堆外内存
 *
 *
 */
public class T04_PhantomReference {

    private static final List<Object> LIST = new LinkedList<>();
    private static final ReferenceQueue<M> QUEUE = new ReferenceQueue<>();


    public static void main(String[] args) {

        PhantomReference<M> phantomReference = new PhantomReference<>(new M(),QUEUE);

        new Thread(() -> {
            while (true) {
                LIST.add(new byte[1024 * 1024]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(phantomReference.get());
            }
        }).start();

        new Thread(() -> {
            while (true) {
                Reference<? extends M> poll = QUEUE.poll();
                if (null != poll) {
                    System.out.println("虚引用对象被JVM回收了：" + poll);
                }
            }
        }).start();


        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
