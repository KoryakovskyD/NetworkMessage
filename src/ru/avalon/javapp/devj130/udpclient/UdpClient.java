package ru.avalon.javapp.devj130.udpclient;

import ru.avalon.javapp.devj130.common.NetworkMessage;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpClient {
    public static final int PORT = 20220;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        if (args.length < 1) {
            System.out.println("Usage: <udp-client> <server-address>");
            return;
        }

        System.out.println("Enter text to send to the server: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text = br.readLine();

        NetworkMessage msg = new NetworkMessage(text);

        byte[] buf;
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(msg);
            buf = baos.toByteArray();
        }
        DatagramPacket dp = new DatagramPacket(buf, buf.length, InetAddress.getByName(args[0]), PORT);
        try(DatagramSocket sock = new DatagramSocket()) {
            sock.send(dp);

            buf = new byte[4096];
            dp = new DatagramPacket(buf, buf.length);
            sock.receive(dp);

            try(ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(buf, 0, dp.getLength()))) {
                msg = (NetworkMessage) ois.readObject();
            }

            System.out.println("Message processing time: " + msg.getProcessingTime());
        }
    }
}
