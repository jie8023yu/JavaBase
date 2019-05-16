package com.com.wj.concurrent.test.threadpool;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleHttpServer {


    static ThreadPool<HttpRequestHandler> threadPool = new DefaultThreadPool<>(10);
    static String basePath;
    static ServerSocket serverSocket;
    static int port = 8080;


    public static void main(String[] args) {
        setBasePath("E:/test/html");
        try {
            start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setPort(int port) {
        if (port > 0) SimpleHttpServer.port = port;
    }

    public static void setBasePath(String basePath) {
        if (null != basePath && new File(basePath).exists() && new File(basePath).isDirectory()) {
            SimpleHttpServer.basePath = basePath;
        }
    }

    public static void start() throws Exception {
        serverSocket = new ServerSocket(port);
        Socket socket = null;
        while (null != (socket = serverSocket.accept())) {
//            System.out.println("一次请求");
            threadPool.execute(new HttpRequestHandler(socket));
        }
        serverSocket.close();
    }



    static class HttpRequestHandler implements Runnable {

        private Socket socket;

        public HttpRequestHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            String line = null;
            BufferedReader br = null;
            BufferedReader reader = null;
            PrintWriter out = null;
            InputStream in = null;
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String header = reader.readLine();
                String filePath = basePath + header.split(" ")[1];
                out = new PrintWriter(socket.getOutputStream());
                if (new File(filePath).exists()) {
                    if (filePath.endsWith("jpg") || filePath.endsWith("ico")) {
                        in = new FileInputStream(filePath);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        int i = 0;
                        while (-1 != (i = in.read())) baos.write(i);
                        byte[] array = baos.toByteArray();
                        out.println("HTTP/1.1 200 OK");
                        out.println("Server:Molly");
                        out.println("Content-Type:image/jpg");
                        out.println("Content-Length:" + array.length);
                        out.println("");
                        out.flush();
                        socket.getOutputStream().write(array,0,array.length);
                    } else {
                        br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
                        out = new PrintWriter(socket.getOutputStream());
                        out.println("HTTP/1.1 200 OK");
                        out.println("Server:Molly");
                        out.println("Content-Type:text/html;charset=utf-8");
                        out.println("");
                        while (null != (line = br.readLine())) out.println(line);
                    }
                }
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
                out.println("HTTP/1.1 500");
                out.println("");
                out.flush();
            } finally {
                close(br,in,reader,out,socket);
            }
        }
    }

    private static void close(Closeable...closeables) {
        if (null != closeables) {
            for (Closeable closeable : closeables) {
                try {
                    if (null != closeable) closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
