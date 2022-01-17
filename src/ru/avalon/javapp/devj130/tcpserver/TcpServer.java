package ru.avalon.javapp.devj130.tcpserver;

import java.io.IOException;
import java.net.ServerSocket;

public class TcpServer {
    public static final int PORT = 20220;

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        try (ServerSocket srvSock = new ServerSocket(PORT, 2)) {
            while (true) {
                new ClientThread(srvSock.accept()).start();
            }
        }
    }
}
