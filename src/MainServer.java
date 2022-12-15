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
                BufferedWriter writer =
                        new BufferedWriter(
                            new OutputStreamWriter(
                                    socket.getOutputStream()));
                BufferedReader reader =
                        new BufferedReader(
                            new InputStreamReader(
                                    socket.getInputStream()));

                String request = reader.readLine();
                System.out.println("Request: " + request);
                String response = (int)(Math.random() * 30 - 10) + "";
                System.out.println("Response: " + response);
                writer.write(response);
                writer.newLine();
                writer.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {throw new RuntimeException(e);}
    }
}
