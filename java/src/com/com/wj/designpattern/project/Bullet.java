package com.com.wj.designpattern.project;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet {

    private static final int SPEED = 10;

    public static int WIDTH = ResourceMgr.bulletL.getWidth();
    public static int HEIGHT = ResourceMgr.bulletL.getHeight();

    private int x,y;
    private Dir dir;

    private boolean live = true;

    private TankFrame tf;

    public Bullet(int x,int y,Dir dir,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g) {

        if (!live) {
            tf.bullets.remove(this);
        }

       /* Color c = g.getColor();
        g.setColor(Color.RED);
        //椭圆
        g.fillOval(x,y,WIDTH,HEIGHT);
        g.setColor(c);*/
        BufferedImage bufferedImage = null;
        switch (dir) {
            case LEFT:
                bufferedImage = ResourceMgr.bulletL;
                break;
            case RIGHT:
                bufferedImage = ResourceMgr.bulletR;
                break;
            case UP:
                bufferedImage = ResourceMgr.bulletU;
                break;
            case DOWN:
                bufferedImage = ResourceMgr.bulletD;
                break;
        }

        if (null != bufferedImage) g.drawImage(bufferedImage,x,y,null);
        move(g);
    }

    private void move(Graphics g) {
        //画矩形，x，y轴上的坐标，宽度和高度
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }

        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) live = false;
    }

}
