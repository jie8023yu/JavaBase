package com.com.wj.concurrent.test.threadpool;

public interface ThreadPool<Job extends Runnable> {

    void execute(Job job);
    void shutdown();
    void addWorkers(int num);
    void removeWorkers(int num);
    /**
     * 获得正在等待执行任务的数量
     * @return
     */
    int getJobSize();

}
