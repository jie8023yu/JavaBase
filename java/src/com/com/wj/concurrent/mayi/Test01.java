package com.com.wj.concurrent.mayi;

import java.util.Date;

public class Test01 {

    public static void main(String[] args) throws InterruptedException {

        //线程几种分类，用户线程，守护线程
        //主线程，子线程，GC线程
        //多线程创建方式，继承Thread，实现Runnable，使用匿名内部类方式，使用线程池方式


        Test test = new Test();
        test.join();

        for (int i = 0;i < 10;i++) System.out.println(i);

    }
}

class Test {

    boolean flag = true;

    public synchronized void join() throws InterruptedException {
//        while (flag) wait();
        wait(0);
        System.out.println(new Date());
    }

}
