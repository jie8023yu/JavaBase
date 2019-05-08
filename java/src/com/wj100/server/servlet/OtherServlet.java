package com.wj100.server.servlet;

import com.wj100.server.Request2;
import com.wj100.server.Response;

public class OtherServlet implements Servlet{
    @Override
    public void service(Request2 request, Response response) {
        response.print("其它配置页面");
    }

    @Override
    public void doGet(Request2 request, Response response) {

    }

    @Override
    public void doPost(Request2 request, Response response) {

    }
}
