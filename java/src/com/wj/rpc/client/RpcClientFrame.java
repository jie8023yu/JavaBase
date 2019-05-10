package com.wj.rpc.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

public class RpcClientFrame {

    public static <T> T getRemoteProxyObject(final Class<?> serviceInterface) throws Exception {
        InetSocketAddress serviceAddr = new InetSocketAddress("127.0.0.1",8888);
        return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(),new Class[]{serviceInterface},new Dymproxy(serviceInterface,serviceAddr));
    }

    private static class Dymproxy implements InvocationHandler {

        private final Class<?> serviceInterface;

        private final InetSocketAddress addr;

        public Dymproxy(Class<?> serviceInterface,InetSocketAddress addr) {
            this.serviceInterface = serviceInterface;
            this.addr = addr;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Socket socket = null;
            ObjectInputStream objectInputStream = null;
            ObjectOutputStream objectOutputStream = null;
            try {
                socket = new Socket();
                //连接到远程主机
                socket.connect(addr);
                //往远端上发数据，按照顺序发送数据 类名 方法名 参数类型 参数值
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeUTF(serviceInterface.getName());
                objectOutputStream.writeUTF(method.getName());
                objectOutputStream.writeObject(method.getParameterTypes());
                objectOutputStream.writeObject(args);
                objectOutputStream.flush();

                //立即拿到远程执行的结果
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                System.out.println("调用成功" + serviceInterface.getName());
                return objectInputStream.readObject();

            } catch (Exception e) {

            } finally {
                if (null != socket) socket.close();
                if (null != objectOutputStream) objectOutputStream.close();
                if (null != objectInputStream) objectInputStream.close();
            }
            return null;
        }
    }
}
