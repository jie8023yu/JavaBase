package com.com.wj.concurrent.test.reference;

import java.lang.ref.WeakReference;

/**
 * 面试最容易问到的
 *
 * 弱引用：只要遭受到GC就会被回收
 */
public class T03_WeakReference {

    public static void main(String[] args) {
        WeakReference<M> m = new WeakReference<>(new M());

        System.out.println(m.get());

        System.gc();

        System.out.println(m.get());

        ThreadLocal<M> t1 = new ThreadLocal<>();
        t1.set(new M());
        t1.remove();
    }
}
