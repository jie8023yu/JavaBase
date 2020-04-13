package com.com.wj.designpattern.project;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import static com.com.wj.designpattern.project.Dir.*;

public class TankFrame extends Frame {


    static final int GAME_WIDTH = 800,GAME_HEIGHT = 900;


    Tank tank = new Tank(200,200,Dir.DOWN,this);
    Bullet bullet = null;

    ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    ArrayList<Tank> tanks = new ArrayList<>();
    
    public TankFrame() {
        //设置大小
       setSize(GAME_WIDTH,GAME_HEIGHT);
        //设置窗口不可摇曳大小
       setResizable(false);
        //设置标题
       setTitle("tank war");
        //设置窗口可见
       setVisible(true);

        //这样写是关闭不了窗口的，如果想要关闭窗口
       addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

       //添加键盘监听
        this.addKeyListener(new MyKeyListener());
    }

    //双缓存解决闪烁问题
    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics graphics = offScreenImage.getGraphics();
        Color c = graphics.getColor();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        graphics.setColor(c);
        paint(graphics);
        //注意：必须用这个G写回去
        g.drawImage(offScreenImage,0,0,null);

    }

    class MyKeyListener extends KeyAdapter {


        boolean bLeft = false;
        boolean bRight = false;
        boolean bUp = false;
        boolean bDown = false;


        /**
         * 某个键被按下的时候调用
         * @param e
         */
        @Override
        public void keyPressed(KeyEvent e) {
//            x += 10;
//            //重新调用一个paint，重画
//            repaint();
            int key = e.getKeyCode();
            switch(key) {
                case KeyEvent.VK_LEFT:
                    bLeft = true;
                    break;
                case KeyEvent.VK_UP:
                    bUp = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bRight = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bDown = true;
                    break;
                case KeyEvent.VK_CONTROL:
                    tank.fire();
                    break;
            }
//            repaint();

            setTankDir();
        }

        private void setTankDir() {

            if (!bLeft && !bRight && !bDown && !bUp) {
                tank.setMoving(false);
            } else {
                tank.setMoving(true);
            }

            if (bLeft) tank.setDir(LEFT);
            if (bRight) tank.setDir(RIGHT);
            if (bDown) tank.setDir(DOWN);
            if (bUp) tank.setDir(UP);


        }

        /*
        某个键别弹起的时候调用
         */
        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch(key) {
                case KeyEvent.VK_LEFT:
                    bLeft = false;
                    break;
                case KeyEvent.VK_UP:
                    bUp = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bRight = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bDown = false;
                    break;
            }
        }
    }


    /**
     * 窗口需要绘制的时候，会被调用
     * @param g
     */
    @Override
    public void paint(Graphics g) {

        for (int i = 0 ; i < tanks.size() ; i++) {
            tanks.get(i).paint(g);
        }

        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量：" + bullets.size(),10,60);
        g.setColor(color);
        //全部交由Tank类来画
        tank.paint(g);
       for (int i = 0 ; i < bullets.size() ; i++) {
           bullets.get(i).paint(g);
       }

       for (int i = 0 ; i < bullets.size() ; i++) {
           bullets.get(i).collectWidth();
       }

    }
}
