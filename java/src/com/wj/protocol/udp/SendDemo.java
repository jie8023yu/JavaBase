package com.wj.protocol.udp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * UDP发送端
 */
public class SendDemo {

    public static void main(String[] args) throws Exception {

        DatagramSocket datagramSocket = new DatagramSocket();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        while (null != (line = bufferedReader.readLine())) {
            byte[] bytes = line.getBytes();
            DatagramPacket datagramPacket = new DatagramPacket(bytes,bytes.length, InetAddress.getByName("127.0.0.1"),10005);
            datagramSocket.send(datagramPacket);
            if ("88".equals(line)) {
                break;
            }
        }
        datagramSocket.close();

    }
}
