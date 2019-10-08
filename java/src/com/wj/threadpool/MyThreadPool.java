package com.wj.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 自定义实现线程池
 */
public class MyThreadPool {

    //线程池中默认的线程个数为5
    private static int WORK_NUM = 5;
    //队列默认的个数为100
    private static int TASK_COUNT = 100;

    //任务队列，作为一个缓冲
    private final BlockingQueue<Runnable> taskQueue;
    private final int worker_num;

    public MyThreadPool() {
        this(WORK_NUM,TASK_COUNT);
    }

    //工作线程
    private WorkThread[] workThreads;


    public MyThreadPool(int worker_num,int taskCount) {
        if (worker_num <= 0) worker_num = WORK_NUM;
        if (taskCount <= 0) taskCount = TASK_COUNT;
        this.worker_num = worker_num;
        this.taskQueue = new ArrayBlockingQueue<>(taskCount);
        workThreads = new WorkThread[worker_num];
        for (int i = 0; i < worker_num; i++) {
            workThreads[i] = new WorkThread();
            workThreads[i].start();
        }


    }

    //执行任务，只是把任务加入任务队列，
    public void execute(Runnable task) {
        try {
            taskQueue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void destroy() {
        System.out.println("ready close pool");
        for (WorkThread workThread : workThreads) {
            workThread.stopWork();
            workThread = null;
        }
        taskQueue.clear();
    }


    @Override
    public String toString() {
        return "WorkThread number:" + worker_num + " wait task number:" + taskQueue.size();
    }

    class WorkThread extends Thread {

        @Override
        public void run() {
            Runnable r = null;
            try {
                while (!isInterrupted()) {
                    r = taskQueue.take();
                    if (null != r) {
                        System.out.println(getId() + " ready execute " + r);
                        r.run();
                    }
                    r = null; //help gc
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        public void stopWork() {
            interrupt();
        }
    }

}
