package Client;

import Protocols.Packet;
import Server.DBWorker;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketClient {

    public static Packet<?> sendPacket(Packet<?> packet) {
        Socket clientSocket = null;
        try {
            clientSocket = new Socket("127.0.0.1", 8000);
            ObjectInputStream reader = new ObjectInputStream(clientSocket.getInputStream());;
            ObjectOutputStream writer = new ObjectOutputStream(clientSocket.getOutputStream());
            Packet<?> receive_packet = null;
            try {
                System.out.println("ПОСОСАЛ");
                writer.writeObject(packet);
                writer.flush();
                receive_packet = (Packet<?>) reader.readObject();
                receive_packet.Print();
            } catch (IOException | ClassNotFoundException e) {e.printStackTrace();} finally {
                try {
                    writer.close(); //
                    reader.close(); //
                    clientSocket.close();
                    System.out.println("Client socket was closed!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return receive_packet;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
