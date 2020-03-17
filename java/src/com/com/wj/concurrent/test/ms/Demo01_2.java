package com.com.wj.concurrent.test.ms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用ReentrantLock
 */
public class Demo01_2<T> {


    final private int MAX = 10;

    private int count;

    private List<T> list = new ArrayList<>();

    private Lock lock = new ReentrantLock();

    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    public void put(T t) {

        try {
            lock.lock();
            while (list.size() == MAX) {
                producer.await();
            }
            list.add(t);
            System.out.println(t);
            ++count;
            consumer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T get() {
        try {
            lock.lock();
            while (list.size() == 0) {
                consumer.await();
            }
            --count;
            producer.signalAll();
            T t = list.remove(count);
            System.out.println("consumer:" + t);
            return t;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return null;
    }

    public static void main(String[] args) {

        Demo01_2<String> d = new Demo01_2<>();
        for (int i = 0 ; i < 8 ; i++) {
            new Thread(() -> {
                for (int j = 0 ; j < 5; j ++) {
                    d.get();
                }

            }).start();
        }

        for (int i = 0 ; i < 2 ; i ++) {
            new Thread(() -> {
                for (int j = 0 ; j < 20; j ++) {
                    d.put(new String("producer-") + new Random().nextInt(100));
                }

            }).start();
        }
    }

}
