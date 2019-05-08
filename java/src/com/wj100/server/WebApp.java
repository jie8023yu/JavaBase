package com.wj100.server;

import com.wj100.server.servlet.Servlet;
import com.wj100.server.servlet.WebContext;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class WebApp {

    private static WebContext context;

    static {
        try {
            //1.获取解析工厂
            SAXParserFactory factory = SAXParserFactory.newInstance();
            //2.从解析工厂获取解析器
            SAXParser saxParser = factory.newSAXParser();
            //3.编写处理器
            //4.加载文档Docum 注册处理器
            WebHandler handler = new WebHandler();
            //5.解析
            saxParser.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("com/wj100/server/web.xml"), handler);
            //获取数据
            /*List<Entity> entities = handler.getEntities();
            for (Entity entity : entities) {
                System.out.println(entity.getName()  + "-->" + entity.getClz());
            }*/
            context = new WebContext(handler.getEntities(), handler.getMappings());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("解析配置文件错误");
        }
    }

    public static Servlet getServletFromUrl(String url) {
        String className = context.getClz("/" + url);
        Class clz = null;
        try {
            clz = Class.forName(className);
            Servlet servlet = (Servlet) clz.newInstance();
            return servlet;
        } catch (Exception e) {

        }

        return null;

    }
}
