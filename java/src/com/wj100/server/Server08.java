package com.wj100.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 多线程加入处理器
 */
public class Server08 {

    private ServerSocket serverSocket;
    private boolean isRunning;

    public static void main(String[] args) {
        Server08 server = new Server08();
        server.start();
        server.receive();

    }

    //启动服务
    public void start() {
        try {
            serverSocket = new ServerSocket(8888);
            isRunning = true;
            System.out.println("服务器启动完毕");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务启动失败");
            stop();
        }
    }

    public void receive() {
        while (isRunning) {
            try {
                Socket client = serverSocket.accept();
                System.out.println("一个客户端建立了连接");
                //多线程处理
                new Thread(new Dispatcher(client)).start();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("客户端错误");
            }
        }
    }

    //停止服务
    public void stop() {
        try {
            serverSocket.close();
            isRunning = false;
            System.out.println("服务器停止");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务器关闭失败");
        }
    }

}
