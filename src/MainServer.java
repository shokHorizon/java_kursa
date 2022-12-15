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

                BufferedWriter writer =
                        new BufferedWriter(
                            new OutputStreamWriter(
                                    socket.getOutputStream()));
                BufferedReader reader =
                        new BufferedReader(
                            new InputStreamReader(
                                    socket.getInputStream()));

                String request = reader.readLine();
                String req1 = (String) ois.readObject();
                //System.out.println(req1 + "ааааааааааааа я чурка");
                System.out.println("You have asked for " + request);
                String response = request + "+ additional info";
                System.out.println("I gave you " + response);
                writer.write(response);
                writer.newLine();
                writer.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {throw new RuntimeException(e);}
    }
}
