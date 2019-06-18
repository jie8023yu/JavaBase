package com.wj.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Client implements Runnable{

    private SocketChannel socketChannel;
    private Selector selector;

    public Client() {
        try {
            this.socketChannel = SocketChannel.open();
            this.selector = Selector.open();
            socketChannel.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {

            SocketAddress socketAddress = new InetSocketAddress("localhost",8888);
            if (socketChannel.connect(socketAddress));
            else socketChannel.register(selector, SelectionKey.OP_READ);
            while (!socketChannel.finishConnect()) {
                ;
            }
            System.out.println("连接成功");
            while (true) {
                selector.select(1000);
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                SelectionKey key = null;
                while (iterator.hasNext()) {
                    key = iterator.next();
                    iterator.remove();
                    if (key.isValid()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        if (key.isConnectable()) {
                            if (channel.finishConnect());
                            else System.exit(1);
                        }


                        if (key.isReadable()) {
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                            int readBytes = channel.read(byteBuffer);
                            if (readBytes > 0) {
                                byteBuffer.flip();
                                byte[] bytes = new byte[byteBuffer.remaining()];
                                byteBuffer.get(bytes);
                                String result = new String(bytes,"utf-8");
                                System.out.println("客户端收到消息" + result);
                            }
                        }
                    }


                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception{
        Client client = new Client();
        new Thread(client).start();
        while (isQuit(new Scanner(System.in).nextLine(),client.socketChannel,client.selector)) {

        }
    }

    public static boolean isQuit(String msg,SocketChannel socketChannel,Selector selector) throws IOException {
        if ("quit".equals(msg)) return false;
        sendMsg(msg,socketChannel,selector);
        return true;
    }

    public static void sendMsg(String msg,SocketChannel socketChannel,Selector selector) throws IOException {
        socketChannel.register(selector,SelectionKey.OP_READ);
        doWrite(socketChannel,msg);

    }

    private static void doWrite(SocketChannel channel,String request) throws IOException{
        //将消息编码为字节数组
        byte[] bytes = request.getBytes();
        //根据数组容量创建ByteBuffer
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        //将字节数组复制到缓冲区
        writeBuffer.put(bytes);
        //flip操作
        writeBuffer.flip();
        //发送缓冲区的字节数组
        channel.write(writeBuffer);
        //****此处不含处理“写半包”的代码
    }


}
