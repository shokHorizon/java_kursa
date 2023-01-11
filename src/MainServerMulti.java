import Client.SocketClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

import static Client.SocketClient.logInfo;


public class MainServerMulti {

    static Logger log = getLogger();

    public static Logger getLogger() {
        return Logger.getLogger(SocketClient.class.getName());
    }
    static final int PORT = 8000;
    static final int MAX_NUMBER_CONNECTIONS = 4;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT, MAX_NUMBER_CONNECTIONS);
        try {
            while (true) {
                logInfo("Сервер ожидает соединения");
                Socket clientSocket = serverSocket.accept();
                logInfo("Сокет открыт: " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());
                try {
                    logInfo("Создался поток");
                    new MainServer(clientSocket);
                }
                catch (IOException e) {
                    e.printStackTrace();
                    clientSocket.close();
                    log.warning("Пойман exception! Сокет закрыт: " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());
                }
            }
        }
        finally {
            serverSocket.close();
        }
    }
}
