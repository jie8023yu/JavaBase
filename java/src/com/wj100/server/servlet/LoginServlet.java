package com.wj100.server.servlet;


import com.wj100.server.Request2;
import com.wj100.server.Response;

public class LoginServlet implements Servlet {

    @Override
    public void service(Request2 request, Response response) {
        //关注内容
        response.print("<html>").print("<head><title>");
        response.print("服务器响应成功</title></head>");
        response.print("<body>欢迎回来：");
        response.print(request.getParameter("username") == null ? "" : request.getParameter("username"));
        response.print("</body><html>");
    }

    @Override
    public void doGet(Request2 Request, Response response) {

    }

    @Override
    public void doPost(Request2 Request, Response response) {

    }
}
