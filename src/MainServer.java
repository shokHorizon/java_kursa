import Protocols.Packet;
import Server.SocketServer;

import java.io.*;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
    public static void main(String[] args) {
        Inet4Address ip;
        int port;

        try (ServerSocket server = new ServerSocket(8000))
        {
            System.out.println("Server started");

            while (true)
            try {
                Socket socket = server.accept(); // сокет под сервак

                ObjectOutputStream ous = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

                Packet response = (Packet) ois.readObject();
                response.Print();
                response.setCode(228);
                ous.writeObject(response);
                ous.flush();

            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {throw new RuntimeException(e);}
    }
}
