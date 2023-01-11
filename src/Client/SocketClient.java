package Client;

import Protocols.Packet;
import Server.DBWorker;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static java.lang.System.exit;

public class SocketClient {


    public static Logger log = getLogger();

    public static Logger getLogger() {
        return Logger.getLogger(SocketClient.class.getName());
    }

    public static void logInfo(String logging) {
        log.info("\u001B[32m" + logging + "\u001B[0m");
    }

    public static Packet<?> sendPacket(Packet<?> packet) {
        Socket clientSocket = null;
        packet.setToken(App.token);
        try {
            clientSocket = new Socket("127.0.0.1", 8000);
            logInfo("Сокет клиента создан");
            ObjectInputStream reader = new ObjectInputStream(clientSocket.getInputStream());;
            ObjectOutputStream writer = new ObjectOutputStream(clientSocket.getOutputStream());
            Packet<?> receive_packet = null;
            try {
                writer.writeObject(packet);
                writer.flush();
                receive_packet = (Packet<?>) reader.readObject();
                //receive_packet.Print();
                logInfo("Пакет передан");
            } catch (IOException | ClassNotFoundException e) {
                log.warning("Пакет не был передан.");
                //e.printStackTrace();
                } finally {
                try {
                    writer.close(); //
                    reader.close(); //
                    clientSocket.close();
                    logInfo("Сокет клиента закрыт");
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
