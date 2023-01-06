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
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client #" + ++count + " socket accepted.");
                try {
                    new MainServer(clientSocket);
                }
                catch (IOException e) {
                    e.printStackTrace();
                    clientSocket.close();
                }
            }
        }
        finally {
            serverSocket.close();
        }
    }
}
