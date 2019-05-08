package com.wj100.server;

import com.wj100.server.servlet.LoginServlet;
import com.wj100.server.servlet.RegisterServlet;
import com.wj100.server.servlet.Servlet;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 加入了servvlet，解耦了业务代码
 */
public class Server06 {

    private ServerSocket serverSocket;

    public static void main(String[] args) {
        Server06 server = new Server06();
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
            if (null != usernames) {
                for (String str : usernames) System.out.println(str);
            }

            Response response = new Response(client);
            Servlet servlet = null;
            String url = request.getUrl().trim();
            if (url.equals("login")) {
                servlet = new LoginServlet();
            } else if (url.equals("reg")) {
                servlet = new RegisterServlet();
            } else {
                //首页
            }
            servlet.service(request,response);
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
