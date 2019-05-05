package com.wj100.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 封装请求信息
 */
public class Server04 {

    private ServerSocket serverSocket;

    public static void main(String[] args) {
        Server04 server = new Server04();
        server.start();
        server.receive();
    }

    //启动服务
    public void start() {
        try {
            serverSocket = new ServerSocket(8888);
            System.out.println("服务器启动完毕");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务启动失败");
        }
    }

    public void receive() {
        try {
            Socket client = serverSocket.accept();
            System.out.println("一个客户端建立了连接");
            //获取请求协议
            Request request = new Request(client);
            Response response = new Response(client);
            //关注内容
            response.print("<html>").print("<head><title>");
            response.print("服务器响应成功</title></head>");
            response.print("<body>server4</body><html>");
            //关注状态码
            response.pushToBrowser(200);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("客户端错误");
        }
    }

    //停止服务
    public void stop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务器关闭失败");
        }
    }

}
