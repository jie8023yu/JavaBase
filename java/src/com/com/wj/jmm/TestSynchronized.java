package com.com.wj.jmm;

public class TestSynchronized {

    public synchronized void test() {
        System.out.println("synchronzied");
    }

    /**
     *
     * 只有通过这种显式的同步同向，才能看到monitor指令，
     * 如果是synchronized method这种，虽然也是同步当前对象，但是从指令集上
     * 是不到monitor相关指令的
     *
     * 通过查看JVM指令，可以看到monitorenter,monitorexit指令，
     * 同时发现monitorexit会有两条，是因为，在发生异常时，会自动释放锁
     */


    void m() {
        synchronized (this) {

        }
    }
}
