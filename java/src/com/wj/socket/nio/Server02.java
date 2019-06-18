package com.wj.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Server02 {


    public static void main(String[] args) {
        try {
            Selector selector = Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            //设置通道非阻塞
            serverSocketChannel.configureBlocking(false);
            //绑定连接 监听端口
            serverSocketChannel.bind(new InetSocketAddress("localhost",8888));
            System.out.println("服务启动");
            //注册
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    if (selectionKey.isValid()) {
                        iterator.remove();
                        if (selectionKey.isAcceptable()) {
                            System.out.println("服务连接成功");
                            ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                            SocketChannel socketChannel = channel.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector,SelectionKey.OP_READ);
                        }
                        if (selectionKey.isReadable()) {
                            System.out.println("服务器开始读取数据");
                            SocketChannel channel = (SocketChannel) selectionKey.channel();
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                            int readLength = channel.read(byteBuffer);
                            if (readLength > 0) {
                                byteBuffer.flip();
                                byte[] bytes = new byte[byteBuffer.remaining()];
                                byteBuffer.put(bytes);
                                String requestStr = new String(bytes,"utf-8");
                                System.out.println("请求的数据为：" + requestStr);
                                String respStr = "哈哈";
                                if ("1".equals(requestStr)) {
                                    respStr = "hello world";
                                } else if ("2".equals(requestStr)) {
                                    respStr = "once complie";
                                }

                                System.out.println("服务器写数据");
                                byte[] writeBytes = respStr.getBytes();
                                ByteBuffer writeBuffer = ByteBuffer.allocate(writeBytes.length);
                                writeBuffer.put(writeBytes);
                                writeBuffer.flip();
                                SocketChannel writeChannel = (SocketChannel) selectionKey.channel();
                                writeChannel.write(writeBuffer);
                            }

                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
