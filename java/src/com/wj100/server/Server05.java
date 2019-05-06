package com.wj100.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 封装请求信息
 */
public class Server05 {

    private ServerSocket serverSocket;

    public static void main(String[] args) {
        Server05 server = new Server05();
        server.start();
        while (true) {
            try {
                server.receive();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

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
            Request2 request = new Request2(client);
            String[] usernames = request.getParameterValues("username");
            for (String str : usernames) System.out.println(str);
            Response response = new Response(client);
            //关注内容
            response.print("<html>").print("<head><title>");
            response.print("服务器响应成功</title></head>");
            response.print("<body>server5");
            response.print(request.getParameter("username"));
            response.print("</body><html>");
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
