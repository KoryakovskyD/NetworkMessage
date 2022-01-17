package ru.avalon.javapp.devj130.tcpserver;

import ru.avalon.javapp.devj130.common.NetworkMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    public static final int PORT = 20220;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try (ServerSocket srvSock = new ServerSocket(PORT)) {
            while (true) {
                try(Socket sock = srvSock.accept();
                    ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
                    ObjectInputStream ois = new ObjectInputStream(sock.getInputStream())) {
                    NetworkMessage msg = (NetworkMessage) ois.readObject();

                    System.out.println("Message has been received from " +
                            sock.getRemoteSocketAddress());
                    System.out.println(msg.getMessage());
                    msg.markProcessingTime();
                    System.out.println("Processing time is " + msg.getProcessingTime());
                    System.out.println();

                    oos.writeObject(msg);
                }
            }
        }
    }
}
