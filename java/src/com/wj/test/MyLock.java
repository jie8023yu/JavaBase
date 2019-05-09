package com.wj.test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * JDK的锁，可重入锁
 */
public class MyLock implements Lock {

    AtomicReference<Thread> owner = new AtomicReference<>();
    public LinkedBlockingQueue<Thread> waitQueue = new LinkedBlockingQueue<>(); //等待列表

    @Override
    public void lock() {
        while (!owner.compareAndSet(null,Thread.currentThread())) {
            //如果获取不到锁（关注点）
            waitQueue.add(Thread.currentThread());
            LockSupport.park(); //让当前线程等待

            waitQueue.remove(Thread.currentThread());

        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        if (owner != null && owner.compareAndSet(Thread.currentThread(),null)) {
            Object[] objects = waitQueue.toArray();
            for (Object object : objects) {
                Thread next = (Thread) object;
                //唤醒
                LockSupport.unpark(next);
            }
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
