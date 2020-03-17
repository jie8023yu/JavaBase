package com.com.wj.concurrent.test.container;

import java.util.concurrent.LinkedTransferQueue;

public class TestTransferQueue {

    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue queue = new LinkedTransferQueue();


        new Thread(() -> {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


//        queue.add("test");
        //必须等待有反馈后才能返回
        queue.transfer("test");


    }
}
