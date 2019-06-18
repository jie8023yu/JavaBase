package com.wj.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class Client02 {

    public static void main(String[] args) {

        try {
            Selector selector = Selector.open();
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress(8888));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
