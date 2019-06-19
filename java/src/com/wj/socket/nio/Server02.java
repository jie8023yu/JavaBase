package com.wj.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Server02 {


    public static void main(String[] args) {
        try {
            //多路复用器，选择器，用于注册通道的   开启多路复用器
            Selector selector = Selector.open();
            //开启服务通道
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            //设置通道非阻塞
            serverSocketChannel.configureBlocking(false);
            //绑定连接 监听端口
            serverSocketChannel.bind(new InetSocketAddress("localhost",8888));
            //注册
            /**
             * 状态编码
             *      OP_ACCEPT   连接成功的标记位
             *      OP_READ     可以读取数据的标志位
             *      OP_WRITE    可以写入数据集的标志位
             *      OP_CONNECT  建立连接后的标记
             */
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("服务启动");
            while (true) {
                //阻塞方法，至少一个通道被选中，此方法返回
                //通道是否选中，由注册到多路复用器中的通道标记决定
                selector.select(1000);
                //返回已选中的通道标记集合
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();
                    try {
                        if (selectionKey.isValid()) {
                            if (selectionKey.isAcceptable()) {
                                System.out.println("服务连接成功");
                                ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                                //阻塞方法，当客户端发起请求后返回
                                SocketChannel socketChannel = channel.accept();
                                socketChannel.configureBlocking(false);
                                socketChannel.register(selector,SelectionKey.OP_READ);
                            }
                            if (selectionKey.isReadable()) {
                                System.out.println("服务器开始读取数据");
                                SocketChannel channel = (SocketChannel) selectionKey.channel();
                                ByteBuffer byteBuffer = ByteBuffer.allocate(1);
                                //将通道中的数据读取到缓存中，通道中的数据就是客户端发送给服务器的数据
                                int readLength = channel.read(byteBuffer);
                                if (readLength > 0) {
                                    //flip NIO中最复杂的操作就是Buffer的控制
                                    /**
                                     * Buffer中有一个游标，游标信息在操作后不会归零。如果直接访问Buffer的话，数据有不一致的可能。
                                     * flip是重置游标的方法。在NIO编程中，flip方法是常用方法
                                     */
                                    byteBuffer.flip();
                                    //字节数组，保存具体数据  byteBuffer.reamining方法是获取Buffer中有效数据长度
                                    byte[] bytes = new byte[byteBuffer.remaining()];
                                    //将buffer中的数据保存到bytes字节数组中
                                    byteBuffer.get(bytes);
                                    String requestStr = new String(bytes,"utf-8");
                                    System.out.println("请求的数据来源：" + channel.getRemoteAddress() +",请求的数据为：" + requestStr);
                                    /*String respStr = "哈哈";
                                    if ("1".equals(requestStr)) {
                                        respStr = "hello world";
                                    } else if ("2".equals(requestStr)) {
                                        respStr = "once complie";
                                    }

                                    System.out.println("服务器写数据");
                                    respStr = new Scanner(System.in).nextLine();
                                    byte[] writeBytes = respStr.getBytes();
                                    ByteBuffer writeBuffer = ByteBuffer.allocate(writeBytes.length);
                                    writeBuffer.put(writeBytes);
                                    writeBuffer.flip();
                                    SocketChannel writeChannel = (SocketChannel) selectionKey.channel();
                                    writeChannel.write(writeBuffer);*/
                                    //注册写通道
                                    channel.register(selector,SelectionKey.OP_WRITE);
                                } else {
                                    selectionKey.channel().close();
                                    selectionKey.cancel();
                                }

                            }

                            if (selectionKey.isWritable()) {
                                System.out.println("服务器端响应数据：");
                                SocketChannel channel = (SocketChannel) selectionKey.channel();
                                String line = new Scanner(System.in).nextLine();
                                ByteBuffer byteBuffer = ByteBuffer.allocate(line.getBytes().length);
                                byteBuffer.put(line.getBytes());
                                byteBuffer.flip();
                                channel.write(byteBuffer);
                                channel.register(selector,SelectionKey.OP_READ);
                            }
                        }
                    } catch (Exception e) {
                        selectionKey.cancel(); //端开连接
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
