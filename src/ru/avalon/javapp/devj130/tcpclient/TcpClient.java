package ru.avalon.javapp.devj130.tcpclient;

import ru.avalon.javapp.devj130.common.NetworkMessage;

import java.io.*;
import java.net.Socket;

public class TcpClient {
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

        try (Socket sock = new Socket(args[0], PORT)) {

            try (ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
                    ObjectInputStream ois = new ObjectInputStream(sock.getInputStream())) {
                oos.writeObject(msg);
                msg = (NetworkMessage) ois.readObject();
                System.out.println(msg.getProcessingPeriod());
            }
        }
    }
}
