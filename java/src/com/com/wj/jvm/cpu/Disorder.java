package com.com.wj.jvm.cpu;

/**
 * 证明乱序执行
 */
public class Disorder {

    private static int x,y,a,b;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (;;) {
            i++;
            x = 0; y = 0;
            a = 0; b = 0;
            Thread one = new Thread(() -> {

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                a = 1;
                x = b;

            });

            Thread two = new Thread(() -> {
                b = 1;
                y = a;
            });

            one.start();
            two.start();

            one.join();
            two.join();

            String result = "第" + i + "次 (" + x + "," + y + ")";
            if (x == 0 && y == 0) {
                System.err.println(result);
                break;
            } else {

            }
        }
    }

}
