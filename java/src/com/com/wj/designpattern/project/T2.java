package com.com.wj.designpattern.project;

/**
 * 第二个版本
 */
public class T2 {

    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();

        //初始化敌方坦克
        for (int i = 0 ;i < 5 ; i++) {
            tankFrame.tanks.add(new Tank(40 + i * Tank.WIDTH,60,Dir.DOWN,tankFrame));
        }
        while (true) {
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
