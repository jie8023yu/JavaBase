package com.wj100.server;

import com.wj100.server.servlet.Servlet;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 分发器
 */
public class Dispatcher implements Runnable{

    private Socket client;
    private Request2 request;
    private Response response;

    public Dispatcher(Socket client) {
        try {
            this.client = client;
            this.request = new Request2(client);
            this.response = new Response(client);
        } catch (IOException e) {
            e.printStackTrace();
            this.release();
        }

    }

    @Override
    public void run() {
        try {
            if (null == request.getUrl() || "".equals(request.getUrl())) {
                InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("index.html");
                byte[] buf = new byte[is.available()];
                is.read(buf);
                response.print(new String(buf));
                response.pushToBrowser(200);
                is.close();
                return;
            }
            //获取请求协议
            String url = request.getUrl().trim();
            Servlet servlet = WebApp.getServletFromUrl(url);
            if (null != servlet) {
                servlet.service(request,response);
                //关注状态码
                response.pushToBrowser(200);
            } else {
                //关注状态码
                InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("error.html");
                byte[] buf = new byte[is.available()];
                is.read(buf);
                response.print(new String(buf));
                response.pushToBrowser(404);
                is.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.print("服务器维护中....");
                response.pushToBrowser(500);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            this.release();
        }
    }

    /**
     * 释放资源
     */
    private void release() {
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
