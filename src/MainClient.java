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
                Packet packet1 = new Packet(null, null);

            Packet<Users> userPacket = new Packet<>(
                    QueryModel.Users,
                    QueryMethod.Read,
                    new Users(
                            0,
                            "opa",
                            0000,
                            0
                    )
            );


//                for (int i = 0; i < 10; i++) {
//                    System.out.println(i);
//                }

            ous.writeObject(userPacket); // Только в ous есть writeobject
            ous.flush();

                Packet receive_packet = new Packet();
                receive_packet = (Packet) ois.readObject();
                receive_packet.Print();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
