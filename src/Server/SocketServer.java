package Server;

import java.io.IOException;
import java.net.*;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SocketServer {
    private ServerSocket server;

    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    public ExecutorService pool = Executors.newFixedThreadPool(4);

    SocketServer(Inet4Address ip, int port) throws IOException {
        server = new ServerSocket();
        server.bind(new InetSocketAddress(ip, port));
    }

    public void serve() throws IOException{
        while (true) {
            System.out.println("[SERVER] waiting for cocks");
            Socket client = server.accept();
            System.out.println("[SERVER] cocks found");
            ClientHandler clientHandler = new ClientHandler(client);
            clients.add(clientHandler);

            pool.execute(clientHandler);
        }
    }
}
