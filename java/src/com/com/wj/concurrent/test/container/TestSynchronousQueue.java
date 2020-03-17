package com.com.wj.concurrent.test.container;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * 容量为0
 * 不能往里面装东西，只能在那里阻塞，两个线程交换数据，一个线程给另外一个线程传数据
 * 不能使用add方法进行添加，如果使用add，会报错
 * java.lang.IllegalStateException: Queue full
 *
 *
 * 如果使用put方法，会等待消费，才会
 *
 */
public class TestSynchronousQueue {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new SynchronousQueue<>();
        //容量为0
//        queue.add("test");

        new Thread(() -> {
            try {
                while (true) {
                    String str = queue.take();
                    System.out.println(str);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        queue.put("test");
        for (int i = 0 ; i < 3 ; i++) {
            queue.put("test" + i);
        }
//        queue.take();
        System.out.println(queue.size());



    }
}
