import Models.Travels;
import Models.Users;
import Protocols.Packet;
import Protocols.QueryMethod;
import Protocols.QueryModel;

import java.io.*;
import java.net.Inet4Address;
import java.net.Socket;

public class MainClient {
    public static void main(String[] args) {
        Inet4Address ip;
        int port;

        try {
            Socket socket = new Socket("127.0.0.1", 8000);

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream ous = new ObjectOutputStream(socket.getOutputStream());

            System.out.println("Connected to server");

            Packet packet = new Packet(QueryModel.Users, null);

            Packet<Travels> userPacket = new Packet<>(
                    QueryModel.Travels,
                    QueryMethod.Read,
                    new Travels(
                            0,
                            0,
                            null,
                            0,
                            null,
                            null,
                            0,
                            0
                    )
            );

            ous.writeObject(userPacket); // Только в ous есть writeobject
            ous.flush();

            Packet receive_packet;
            receive_packet = (Packet) ois.readObject();
            receive_packet.Print();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
