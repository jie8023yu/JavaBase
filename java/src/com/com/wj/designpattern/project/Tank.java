package com.com.wj.designpattern.project;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tank {

    private int x ;
    private int y ;
    private Dir dir ;

    public static int WIDTH = ResourceMgr.tankL.getWidth();
    public static int HEIGHT = ResourceMgr.tankL.getHeight();



    private static final int SPEED = 5;

    private boolean moving = false;

    private TankFrame tankFrame;


    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Dir getDir() {
        return dir;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Tank(){
        this.x = 200;
        this.y = 200;
        this.dir = Dir.LEFT;
    }

    public Tank(int x,int y,Dir dir,TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
    }

    public void paint(Graphics g) {
//        Color c = g.getColor();
        /*g.setColor(Color.YELLOW);
        g.fillRect(x,y,50,50);
        g.setColor(c);*/

        BufferedImage bufferedImage = null;
        switch (dir) {
            case LEFT:
                bufferedImage = ResourceMgr.tankL;
                break;
            case RIGHT:
                bufferedImage = ResourceMgr.tankR;
                break;
            case UP:
                bufferedImage = ResourceMgr.tankU;
                break;
            case DOWN:
                bufferedImage = ResourceMgr.tankD;
                break;
        }

        if (null != bufferedImage) g.drawImage(bufferedImage,x,y,null);

//        g.drawImage(ResourceMgr.tankL,x,y,null);
        if (moving) move(g);
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
    }

    public void fire() {
        int bx = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        tankFrame.bullets.add(new Bullet(bx,by,this.dir,tankFrame));
    }
}
