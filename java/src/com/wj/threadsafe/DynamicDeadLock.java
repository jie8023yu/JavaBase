package com.wj.threadsafe;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 动态死锁
 *
 * System . identityHashCode方法  hash冲突的概率在千万分之一
 *
 * 直接使用对象的hashcode方法，可能会被重写，使用上面这个
 *
 */
public class DynamicDeadLock {


    private static Object lock = new Object(); //用来解决hash冲突的

    private static CountDownLatch countDownLatch = new CountDownLatch(2);

    /**
     * @param from  转钱方
     * @param to    收钱方
     * @param money 多少钱
     */
    public void transform(Account from,Account to,int money) {
        synchronized (from) {
            System.out.println(from.getName() + " synchronized");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (to) {
                System.out.println(to.getName() + " synchronized");
                System.out.println(from.getName() + "给" + to.getName() + "转钱：" + money);
            }
        }
    }

    /**
     * 动态冲突解决方案1 让其按照规定的顺序去拿锁
     * @param from
     * @param to
     * @param money
     */
    public void transform2(Account from,Account to,int money) {
        int fromHash = System.identityHashCode(from);
        int toHash = System.identityHashCode(to);
        //比较hash来确定顺序
        if (fromHash < toHash) {
            synchronized (from) {
                System.out.println(Thread.currentThread().getName() + "," + from.getName() + " synchronized");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (to) {
                    System.out.println(Thread.currentThread().getName() + "," + to.getName() + " synchronized");
                    from.setMoney(from.getMoney() - money);
                    to.setMoney(to.getMoney() + money);
                    System.out.println("完成了操作");
                }
            }
        } else if (fromHash > toHash) {
            synchronized (to) {
                System.out.println(Thread.currentThread().getName() + "," + to.getName() + " synchronized");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (from) {
                    System.out.println(Thread.currentThread().getName() + "," + from.getName() + " synchronized");
                    from.setMoney(from.getMoney() - money);
                    to.setMoney(to.getMoney() + money);
                    System.out.println("完成了操作");
                }
            }
        } else { //如果发生了Hash冲突，解决方案
            synchronized (lock) {
                synchronized (from) {
                    System.out.println(Thread.currentThread().getName() + "," + from.getName() + " synchronized");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (to) {
                        System.out.println(Thread.currentThread().getName() + "," + to.getName() + " synchronized");
                        from.setMoney(from.getMoney() - money);
                        to.setMoney(to.getMoney() + money);
                        System.out.println("完成了操作");
                    }
                }
            }
        }
    }


    /**
     * 动态冲突解决方案1 通过显式锁的方式去获取
     */
    public void transform3(Account from,Account to,int money) throws InterruptedException {
        Random r = new Random();
        while (true) {
            //尝试拿锁
            if (from.getLock().tryLock()) {
                System.out.println(Thread.currentThread().getName() + "," + from.getName() + " get lock");
                try {
                    if (to.getLock().tryLock()) {
                        try {
                            //两把锁都拿到了
                            System.out.println(Thread.currentThread().getName() + "," + to.getName() + " get lock");
                            from.setMoney(from.getMoney() - money);
                            to.setMoney(to.getMoney() + money);
                            break;
                        } finally {
                            to.getLock().unlock();
                        }
                    }
                } finally {
                    from.getLock().unlock();
                }
            }
//            Thread.sleep(r.nextInt(10));  //如果不设置睡眠，可能就会有活锁情况的出现 由于这种显式锁太有礼貌，拿不到第二把锁就放弃第一把锁，如果不睡眠，会造成双方频繁的取到第一把锁
            //但不能取到第二把锁 的 概率大大增加 造成活锁现象的出现
        }
    }


    public static void main(String[] args) {
        DynamicDeadLock dynamicDeadLock  = new DynamicDeadLock();
        Account account = new Account(1,"test",100);
        Account account2 = new Account(2,"test2",100);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                dynamicDeadLock.transform(account,account2,10);
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                dynamicDeadLock.transform(account2,account,10);
//            }
//        }).start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                dynamicDeadLock.transform2(account,account2,10);
//                countDownLatch.countDown();
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                dynamicDeadLock.transform2(account2,account,20);
//                countDownLatch.countDown();
//            }
//        }).start();
//
//        try {
//            countDownLatch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(account.toString());
//        System.out.println(account2.toString());


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dynamicDeadLock.transform3(account,account2,10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dynamicDeadLock.transform3(account2,account,10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


}




class Account {

    private int id; //如果能保证ID唯一，也可以使用ID来比较，核心就是保证按照一定的顺序来获取锁
    private String name;
    private int money;

    //解决方式2：通过显示锁来控制
    private final Lock lock = new ReentrantLock();

    public Lock getLock() {
        return lock;
    }

    public Account(int id, String name, int money) {
        this.id = id;
        this.name = name;
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }


    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}
