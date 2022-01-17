package ru.avalon.javapp.devj130.tcpserver;

import ru.avalon.javapp.devj130.common.NetworkMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientThread extends Thread{
    private final Socket clientSocket;

    public ClientThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
            try(ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {
            NetworkMessage msg = (NetworkMessage) ois.readObject();

            String remoteAddr = clientSocket.getRemoteSocketAddress().toString();
            System.out.println("Message has been received from " + remoteAddr);
            System.out.println(msg.getMessage());
            msg.startProcessing();

            System.out.println(remoteAddr + ": Processing srted at  " + msg.getProcessingStartTime());
            Thread.sleep(2*60_000);
            msg.endProcessing();
            System.out.println(remoteAddr + ": Processing ended at " + msg.getProcessingEndTime());

            oos.writeObject(msg);
        } catch (Exception e) {
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                }
            }
    }
}
