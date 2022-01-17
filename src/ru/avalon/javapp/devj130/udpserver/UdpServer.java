package ru.avalon.javapp.devj130.udpserver;

import ru.avalon.javapp.devj130.common.NetworkMessage;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpServer {
    public static final int PORT = 20220;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try(DatagramSocket sock = new DatagramSocket(PORT)) {
            byte[] buf = new byte[4096];
            DatagramPacket dp = new DatagramPacket(buf, buf.length);

            while (true) {
                sock.receive(dp);

                NetworkMessage msg;
                try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(buf, 0, dp.getLength()))) {
                    msg = (NetworkMessage) ois.readObject();
                }

                System.out.println("Message has been received from " +
                        dp.getAddress() + dp.getPort());
                System.out.println(msg.getMessage());
                msg.markProcessingTime();
                System.out.println("Processing time is " + msg.getProcessingTime());
                System.out.println();

                try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                     ObjectOutputStream oos = new ObjectOutputStream(baos)) {
                    oos.writeObject(msg);
                    byte[] ab = baos.toByteArray();
                    DatagramPacket dpToSend = new DatagramPacket(ab, ab.length, dp.getAddress(), dp.getPort());
                    sock.send(dpToSend);
                }
            }
        }
    }
}
