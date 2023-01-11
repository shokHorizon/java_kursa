import DAO.CitiesDao;
import DAO.IDao;
import Protocols.Packet;
import Protocols.QueryModel;
import Server.DBWorker;
import Server.QueryController;

import java.io.*;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static Client.SocketClient.log;
import static Client.SocketClient.logInfo;


public class MainServer extends Thread {

    private Socket clientSocket;
    //private final ObjectInputStream reader;
    //private final ObjectOutputStream writer;
    public MainServer(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        //reader = new ObjectInputStream(clientSocket.getInputStream());
        //writer = new ObjectOutputStream(clientSocket.getOutputStream());
        start();
    }

    public void run() {
        try {
            //Package incomingPackage = (Package) reader.readObject();
            //Package outgoingPackage  = ControllerServer.processRequest(incomingPackage);
            //writer.writeObject(outgoingPackage);
            ObjectOutputStream writer = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream reader = new ObjectInputStream(clientSocket.getInputStream());
            Packet request = (Packet) reader.readObject();
            Packet response = QueryController.query_request(request);

            //response.Print();
            //System.out.println("[Server]");
            //System.out.println("Модель " + request.getQueryModel());
            //System.out.println("Метод " + request.getQueryMethod());
            writer.writeObject(response);
            //System.out.println("Объекты записаны");
            writer.flush();
            writer.close();
//            while (!clientSocket.isClosed()) {
//
//            }
            writer.flush();
        } catch (IOException | ClassNotFoundException e) {e.printStackTrace();}
        finally {
            try {
                clientSocket.close();
                logInfo("Сокет закрыт: " +  clientSocket.getInetAddress() + ":" + clientSocket.getPort());
            } catch (IOException e) {
                log.warning("Сокет не закрылся: " +  clientSocket.getInetAddress() + ":" + clientSocket.getPort());
                throw new RuntimeException(e);
            }
        }
    }



// public static void main(String[] args) {
//        Inet4Address ip;
//        int port;
//
//        try (ServerSocket server = new ServerSocket(8000)) {
//            System.out.println("Server started");
//
//            DBWorker worker = new DBWorker();
//
//            IDao CD = new CitiesDao();
//            CD.getAll();
//            //CD.get(2);
//            //CD.update(2);
//
//            while (true){
//                //new Thread(() -> {
//                    try {
//                        Socket socket = server.accept(); // сокет под сервак
//
//                        ObjectOutputStream ous = new ObjectOutputStream(socket.getOutputStream());
//                        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
//
//                        Packet response = (Packet) ois.readObject();
//                        response.Print();
//                        response.setQueryModel(QueryModel.Users);
//                        ous.writeObject(response);
//                        ous.flush();
//                    } catch (IOException | ClassNotFoundException e) {
//                        throw new RuntimeException(e);
//                    }
//               // }).start();
//            }
//        } catch (IOException e) {throw new RuntimeException(e);}
//    }
}
