import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServerMulti {

    static final int PORT = 8000;
    static int count = 0;
    static final int MAX_NUMBER_CONNECTIONS = 5;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT, MAX_NUMBER_CONNECTIONS);
        try {
            while (true) {
                System.out.println("Waiting for new connections");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Сокет открыт: " + clientSocket.getPort());
                try {
                    new MainServer(clientSocket);
                    System.out.println("Создан новый поток");
                }
                catch (IOException e) {
                    e.printStackTrace();
                    clientSocket.close();
                    System.out.println("Пойман exception! Сокет закрыт: " + clientSocket.getPort());
                }
            }
        }
        finally {
            serverSocket.close(); // !!!
        }
    }
}
