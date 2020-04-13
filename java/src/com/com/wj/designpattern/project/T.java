package com.com.wj.designpattern.project;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 第一个版本
 */
public class T {

    public static void main(String[] args) {
        //创建一个窗口
        Frame frame = new Frame();

        //设置大小
        frame.setSize(800,600);
        //设置窗口不可摇曳大小
        frame.setResizable(false);
        //设置标题
        frame.setTitle("tank war");
        //设置窗口可见
        frame.setVisible(true);

        //这样写是关闭不了窗口的，如果想要关闭窗口
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }

}
