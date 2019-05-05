package com.wj.protocol.tcp;

import java.io.PrintWriter;
import java.net.Socket;

/**
 * TCP客户端
 */
public class TcpClient {

    public static void main(String[] args) throws Exception {
        String msg = "hello";
        //创建一个Socket
        Socket socket = new Socket("127.0.0.1",8888);
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        printWriter.println(msg);
        printWriter.flush();
        //关闭资源
        printWriter.close();
        socket.close();
    }
}
