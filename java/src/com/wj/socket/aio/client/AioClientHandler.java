package com.wj.socket.aio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

public class AioClientHandler implements CompletionHandler<Void,AioClientHandler>,Runnable {

    private AsynchronousSocketChannel channel;
    private String host;
    private int port;
    private CountDownLatch latch;

    public AioClientHandler(String host, int port) {
        this.host = host;
        this.port = port;
        try {
            channel = AsynchronousSocketChannel.open();  //创建一个实际的通道
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AsynchronousSocketChannel getChannel() {
        return channel;
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    @Override
    public void completed(Void result, AioClientHandler attachment) {
        System.out.println("已经连接客户端");
    }

    @Override
    public void failed(Throwable exc, AioClientHandler attachment) {
        System.out.println("连接客户端失败");
        exc.printStackTrace();
        latch.countDown();
        try {
            channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        latch = new CountDownLatch(1);
        channel.connect(new InetSocketAddress(host, port),this,this);
        try {
            latch.await();
            channel.close();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String msg) {
        byte[] bytes = msg.getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        writeBuffer.put(bytes);
        writeBuffer.flip();
        //异步写
        channel.write(writeBuffer,writeBuffer,new AioClientWriteHandler(channel,latch));
    }
}
