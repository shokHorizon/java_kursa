import Protocols.Packet;
import Protocols.QueryModel;
import Server.DBWorker;

import java.io.*;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;



public class MainServer {


    public static void main(String[] args) {
        Inet4Address ip;
        int port;

        try (ServerSocket server = new ServerSocket(8000))
        {
            System.out.println("Server started");

            DBWorker worker = new DBWorker();

//        try {
            //Statement statement = worker.getConnection().createStatement();
            //ResultSet set = statement.executeQuery(query);
            //IDao TD = new TicketsDao();
            //TD.getAll();
            //TD.get(1);
            //IDao TRD = new TripsDao();
//            System.out.println();
//            //TRD.getAll();
//            TRD.get(1);
//            System.out.println();
//            TRD.delete(2);
//            TRD.getAll();
//            System.out.println();
            //TRD.get(1);




            while (true)
            try {
                Socket socket = server.accept(); // сокет под сервак

                ObjectOutputStream ous = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

                Packet response = (Packet) ois.readObject();
                response.Print();
                response.setQueryModel(QueryModel.Users);
                ous.writeObject(response);
                ous.flush();

            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {throw new RuntimeException(e);}
    }
}
