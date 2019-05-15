package com.com.wj.concurrent.test;


public class ThreadA
{

    public static void main(String[] args) throws Exception
    {
        Object lock = new Object();
        ThreadB b = new ThreadB();
        Threadc c = new Threadc(b);
        c.setName("c线程");
        b.setName("b线程");
        c.start();
        System.out.println(Thread.currentThread().getName()+" is start....");
//        Thread.sleep(1000);
        synchronized(c)
        {
            try
            {
                System.out.println("waiting for b1 to complete....");
                c.wait();
                System.out.println("Completed now back to "+
                        Thread.currentThread().getName());
                System.out.println(1);
            }
            catch(InterruptedException e)
            {
            }
        }
    }
}
class ThreadB extends Thread
{
    int total;
    public void run()
    {
        synchronized(this)
        {
            System.out.println(Thread.currentThread().getName()+" is running..");
            for(int i=0;i<10;i++)
            {
                total +=i;
            }
            System.out.println("total is "+total);
        }
    }
}
class Threadc extends Thread
{
    int sum=1;
    Object lock;
    public Threadc(Object lock) {
        this.lock = lock;
    }
    public void run()
    {
        synchronized(this)
        {
            System.out.println(Thread.currentThread().getName()+" is running..");
            for(int i=1;i<10;i++)
            {
                sum *=i;
            }
            System.out.println("sum is "+sum);
            /*if (lock instanceof Thread) {
                ((Thread) lock).start();
//                ((Thread) lock).stop();
            }*/
//            lock.notify();
        }
    }
}
