package com.wj.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO
 *
 * C的select写法：
 * struct sockaddr_in serv;
 * serv.sin_port = htons(8888);
 * serv.sin_addr.s_addr = inet_addr("127.0.0.1");
 * serv.sin_family = AF_INET;
 * int sfd = socket(AF_INET,SOCK_STREAM,0);
 * bind(sfd,&serv,sizeof(serv));  //绑定端口
 * listen(sfd,128);  //监听
 * fd_set reads,temp;
 * FD_ZERO(reads);
 * FD_SET(sfd,reads);
 * int max = sfd;
 * while (1) {
 *     temp = reads;
 *     int ret = select(max + 1,&temp,NULL,NULL,NULL);
 *     //等待内核返回
 *     if (FD_ISSET(sfd,&temp)) {
 *         //代表新连接
 *         struct sockaddr_in client;
 *         int len = sizeof(client);
 *         int cfd = accept(sfd,&client,&len);
 *         FD_SET(cfd,reads);
 *         max = max < cfd ? cfd : max;
 *     }
 *     //接收已经连接的发送数据
 *     for (int i = sfd + 1; i < max + 1; i++){
 *         if (FD_ISSET(i,&temp)) {
 *             char buf[1024] = {0};
 *             int ret = recv(i,buf,sizeof(buf),0);
 *             if (ret > 0) {
 *                 //代表有数据
 *             } else if (ret == 0) {
 *                 //断开连接
 *                 close(i);
 *                 FD_CLR(i,&reads);
 *             }
 *         }
 *     }
 * }
 *
 *
 *
 *
 *
 *
 */
public class Server {

    public static void main(String[] args) {
        new ServerTest("localhost",8888).start();
    }

    private static class ServerTest {

        private String host;
        private int port;
        private boolean isRun = true;
        private Selector selector;
        private ServerSocketChannel serverSocketChannel;
        //初始化


        public ServerTest(String host, int port) {
            this.host = host;
            this.port = port;

            //NIO的入口主类
            try {
                //伦询器
                selector = Selector.open();
                //管道
                serverSocketChannel = ServerSocketChannel.open();
                //非阻塞
                serverSocketChannel.configureBlocking(false);
                serverSocketChannel.socket().bind(new InetSocketAddress(host,port));
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
                System.out.println("server start......");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void start() {
            //事件轮询
            while (isRun) {
                //轮询key
                try {
                    selector.select(1000); //超时时间
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    SelectionKey selectionKey = null;
                    while (iterator.hasNext()) {
                        selectionKey = iterator.next();
                        //处理事件
                        handleKey(selectionKey);
                        iterator.remove();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        //处理事件
        private void handleKey(SelectionKey selectionKey) {
            if (selectionKey.isValid()) {
                if (selectionKey.isAcceptable()) {
                    //由key来获取channel
                    ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                    try {
                        //由channel响应连接
                        SocketChannel socketChannel = channel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector,SelectionKey.OP_READ);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (selectionKey.isReadable()) {
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    //BIO读取数据使用IO模型  NIO使用缓存区/管道
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    try {
                        // channel 管子
                        //ByteBuffer 杯子
                        int readLength = channel.read(readBuffer);
                        if (readLength > 0) {
                            readBuffer.flip(); //防止tcp包不完整
                            byte[] bytes = new byte[readBuffer.remaining()];
                            //复制数据 get:把自己的数据get出来，放到bytes数组中
                            readBuffer.get(bytes);

                            String requestStr = new String(bytes, "utf-8");
                            System.out.println("请求str：" + requestStr);

                            String responseStr = "xx".equals(requestStr) ? "哈哈" : "呵呵";
                            //写回管道
                            writeToChannel(responseStr,channel);
                        } else {
                            //写回管道
                            writeToChannel("哈哈啊哦",channel);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }

        private void writeToChannel(String responseStr, SocketChannel channel) {
            byte[] bytes = responseStr.getBytes();
            ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
            byteBuffer.put(bytes);
            byteBuffer.flip();
            try {
                channel.write(byteBuffer);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
