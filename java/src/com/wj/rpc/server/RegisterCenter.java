package com.wj.rpc.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 服务注册中心
 */
public class RegisterCenter {


    //线程池
    private static ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private static final HashMap<String,Class> serviceRegistry = new HashMap<>();

    private static boolean isRunning = false;

    private static int port;

    public RegisterCenter(int port) {
        this.port = port;
    }


    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(port));
        System.out.println("start server");
        try {
            while (true) {
                executor.execute(new ServiceTask(serverSocket.accept()));
            }
        } catch (Exception e) {

        }
    }

    public void register(Class serviceInterface,Class impl) {
        serviceRegistry.put(serviceInterface.getName(),impl);
    }

    private static class ServiceTask implements Runnable {

        Socket client = null;

        public ServiceTask(Socket client) { this.client = client;}

        @Override
        public void run() {
            ObjectInputStream objectInputStream = null;
            ObjectOutputStream objectOutputStream = null;
            try {
                objectInputStream = new ObjectInputStream(client.getInputStream());
                String serviceName = objectInputStream.readUTF();
                String methodName = objectInputStream.readUTF();
                Class<?>[] paramTypes = (Class<?>[]) objectInputStream.readObject();
                Object[] args = (Object[]) objectInputStream.readObject();

                //到注册中心，获取实现类
                Class serviceClass = serviceRegistry.get(serviceName);
                Method method = serviceClass.getMethod(methodName,paramTypes);
                Object result = method.invoke(serviceClass.newInstance(), args);

                objectOutputStream = new ObjectOutputStream(client.getOutputStream());
                objectOutputStream.writeObject(result);
                objectOutputStream.flush();


            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (null != objectOutputStream) objectOutputStream.close();
                    if (null != objectInputStream) objectInputStream.close();
                    if (null != client) client.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }



}
