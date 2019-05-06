package com.wj100.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.*;

/**
 * 封装请求协议
 */
public class Request2 {

    private String requestInfo;

    private String method;
    private String url;
    private String queryString;

    //存储参数
    private Map<String, List<String>> parameterMap;

    private String CRLF = "\r\n";


    public Request2(Socket client) throws IOException {
        this(client.getInputStream());
    }

    public Request2(InputStream is) {
        parameterMap = new HashMap<>();
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
        //转层Map
        convertMap();
    }

    /**
     * 处理请求参数为Map
     */
    private void convertMap() {
        //分割字符串
        String[] keyValues = this.queryString.split("&");
        for (String queryStr : keyValues) {
            String[] kv = queryStr.split("=");
            kv = Arrays.copyOf(kv,2);
            //获取key和value
            String key = kv[0];
            String value = kv[1];
//            String value = kv[1] == null ? null : decode(kv[1],"utf-8");
            if (parameterMap.containsKey(key)) {
                parameterMap.get(key).add(value);
            } else {
                List<String> list = new ArrayList<>();
                list.add(value);
                parameterMap.put(key,list);
            }
        }
    }

    /**
     * 处理中文
     * @return
     */
    private String decode(String value,String enc) {
        try {
            return java.net.URLDecoder.decode(value,enc);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 通过name获取对应的多个值
     * @param key
     * @return
     */
    public String[] getParameterValues(String key) {
        List<String> list = parameterMap.get(key);
        if (null == list || list.size() < 1) return null;
        return list.toArray(new String[0]);
    }

    /**
     * 通过name获取对应的一个值
     * @param key
     * @return
     */
    public String getParameter(String key) {
        return (parameterMap.get(key) == null || parameterMap.get(key).size() < 1) ? null :  parameterMap.get(key).get(0);
    }


    public String getRequestInfo() {
        return requestInfo;
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public String getQueryString() {
        return queryString;
    }
}
