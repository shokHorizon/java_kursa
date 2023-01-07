package Client;

import Protocols.Packet;
import Server.DBWorker;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketClient {

    public static final SocketClient INSTANCE;

    static {
        try {
            INSTANCE = new SocketClient();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private final Socket clientSocket = new Socket("127.0.0.1", 8000);
    private final ObjectInputStream reader = new ObjectInputStream(clientSocket.getInputStream());;
    private final ObjectOutputStream writer = new ObjectOutputStream(clientSocket.getOutputStream());
    public SocketClient() throws IOException {
    }

    public Packet<?> sendPacket(Packet<?> packet) {
        Packet<?> receive_packet = null;
        try {
            writer.writeObject(packet);
            writer.flush();
            receive_packet = (Packet<?>) reader.readObject();
            receive_packet.Print();
            writer.close(); //
            reader.close(); //
        } catch (IOException | ClassNotFoundException e) {return receive_packet;} finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return receive_packet;
    }
}
