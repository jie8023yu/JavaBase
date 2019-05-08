package com.wj100.server.servlet;

import com.wj100.server.Request2;
import com.wj100.server.Response;

public interface Servlet {

    void service(Request2 request, Response response);

    void doGet(Request2 request, Response response);

    void doPost(Request2 request, Response response);
}
