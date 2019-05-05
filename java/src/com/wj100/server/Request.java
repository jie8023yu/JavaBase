package com.wj100.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 封装请求协议
 */
public class Request {

    private String requestInfo;

    private String method;
    private String url;
    private String queryString;

    private String CRLF = "\r\n";


    public Request(Socket client) throws IOException {
        this(client.getInputStream());
    }

    public Request(InputStream is) {
        byte[] datas = new byte[1024 * 1024];
        int len = 0;
        try {
            len = is.read(datas);
            this.requestInfo = new String(datas,0,len);
            System.out.println(requestInfo);
            //分解字符串
            parseRequestInfo();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

    }

    private void parseRequestInfo() {
        this.method = this.requestInfo.substring(0,this.requestInfo.indexOf("/")).toLowerCase();
        int index1 = this.requestInfo.indexOf("/") + 1;
        int index2 = this.requestInfo.indexOf("HTTP/");
        this.url = this.requestInfo.substring(index1,index2);
        int queryIndex = this.url.indexOf("?");
        if (queryIndex > 0) {
            //表示存在请求参数
            String[] urlArray = this.url.split("\\?");
            this.url = urlArray[0];
            this.queryString = urlArray[1];
        }

        //get方法请求参数在请求行中，如果是post在请求体中

        if (method.equals("post")) {
            String qStr = this.requestInfo.substring(this.requestInfo.lastIndexOf(CRLF)).trim();
            if (null == queryString) {
                queryString = qStr;
            } else {
                queryString += "&" + qStr;
            }
        }
        System.out.println(method);
        System.out.println(url);
        System.out.println(queryString);
    }



}
