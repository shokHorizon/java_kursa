import Server.SocketServer;

import java.io.IOException;
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
            }
        } catch (IOException e) {throw new RuntimeException(e)}
    }
}
