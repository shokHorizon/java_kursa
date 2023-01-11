import Client.SocketClient;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static Client.SocketClient.logInfo;
import static java.lang.System.exit;


public class MainServerMulti {

    static Logger log = getLogger();

    public static Logger getLogger() {
        return Logger.getLogger(SocketClient.class.getName());
    }

    public static void createLogFile() {
        FileHandler fh;
        try {
            File logFile = new File("Server.log");
            if (!logFile.exists()) {
                logFile = new File("./Server.log");
            }
            fh = new FileHandler("Server.log");
            log.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        }
        catch (SecurityException | IOException e) { e.printStackTrace(); }
    }

    public static void logInfo(String logging) {
        log.info("\u001B[32m" + logging + "\u001B[0m");
    }
    static final int PORT = 8000;
    static final int MAX_NUMBER_CONNECTIONS = 4;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT, MAX_NUMBER_CONNECTIONS);
        createLogFile();
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
