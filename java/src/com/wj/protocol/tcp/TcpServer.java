package com.wj.protocol.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP服务器
 */
public class TcpServer {

    public static void main(String[] args) throws Exception {

        //创建一个ServerSocket监听一个端口：8888
        ServerSocket serverSocket = new ServerSocket(8888);

        //无限循环
        while (true) {
            //等待客户端的请求，如果有请求分配一个Socket
            Socket socket = serverSocket.accept();
            //根据标准输入构造一个BufferedReader对象
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String buffer = null;
            //循环读取输入的每一行数据
            while (null != (buffer = br.readLine()) && !"".equals(buffer)) {
                System.out.println(buffer);
            }

            //通过Socket对象得到输出流
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            //charset=UTF-8 注意：空格不能多有 如果Content-Type前面有空格 浏览器访问会乱码
            bw.write("HTTP/1.1 200 OK \r\nContent-Type:text/html;charset=utf-8\r\n\r\n ");
            bw.write("<html><head><title>http请求</title></head><body><h1>这是一个HTTP请求</h1></body></html>");
            //刷新输出流
            bw.flush();
            //关闭
            bw.close();
            br.close();
            socket.close();
            System.out.println("关闭");
        }
    }
}
