package com.wj100.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.cert.CRL;
import java.util.Date;

/**
 *
 */
public class Server02 {

    private ServerSocket serverSocket;

    public static void main(String[] args) {
        Server02 server = new Server02();
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
            InputStream inputStream = client.getInputStream();
            byte[] datas = new byte[1024 * 1024];
            int len = inputStream.read(datas);
            String requestInfo = new String(datas,0,len);
            System.out.println(requestInfo);
            //返回 1.响应行 2.响应头 3.响应体
            StringBuilder content = new StringBuilder();
            content.append("<html>").append("<head><title>");
            content.append("服务器响应成功</title></head>");
            content.append("<body>server</body><html>");
            int size = content.toString().getBytes().length; //注意：此处获取的是字节的长度
            String blank = " ";
            String CRLF = "\r\n";
            StringBuilder responseInfo = new StringBuilder();
            responseInfo.append("HTTP/1.1").append(blank);
            responseInfo.append(200).append(blank);
            responseInfo.append("OK").append(CRLF);
            //响应头
            responseInfo.append("Date:").append(new Date()).append(CRLF);
            responseInfo.append("Server:").append("my server/1.0.1;charset=utf-8").append(CRLF);
            responseInfo.append("Content-Type:text/html").append(CRLF);
            responseInfo.append("Content-length:").append(size).append(CRLF);
            responseInfo.append(CRLF);
            //3.正文
            responseInfo.append(content.toString());

            //写出到客户端
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            bw.write(responseInfo.toString());
            bw.flush();

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
