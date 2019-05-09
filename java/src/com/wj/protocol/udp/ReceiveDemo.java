package com.wj.protocol.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * UDP接收端
 */
public class ReceiveDemo {


    public static void main(String[] args) throws Exception {
        DatagramSocket datagramSocket = new DatagramSocket(10005);
        byte[] bytes = new byte[1024];
        //以一个空数组来创建DatagramPacket,这个对象作用是接收DatagramSocket中的数据
        DatagramPacket datagramPacket = new DatagramPacket(bytes,bytes.length);
        while (true) {
            datagramSocket.receive(datagramPacket);
            //获取接收的数据
            byte[] data = datagramPacket.getData();
            String str = new String(data,0,datagramPacket.getLength());
            System.out.println(str);
            if ("88".equals(str)) {
                break;
            }
        }
        datagramSocket.close();
    }



}
