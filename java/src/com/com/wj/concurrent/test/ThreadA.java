package com.com.wj.concurrent.test;

/**
 * 说明：如果加锁的对象是一个Thread，调用了该线程对象的wait方法，即使没有notify通知，如果该线程执行完毕后（也就是线程没有存活了 isAlive()），
 * wait方法将不会再阻塞，会执行完毕。原因，没找到，目前已知的就这一个特殊。
 */
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
