package com.wj.rpc.server;

import java.io.IOException;

public class Server {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    RegisterCenter registerCenter = new RegisterCenter(8888);
                    registerCenter.register(TechInterface.class, TechImpl.class);
                    registerCenter.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
