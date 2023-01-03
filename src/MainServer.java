import DAO.CitiesDao;
import DAO.IDao;
import Protocols.Packet;
import Protocols.QueryModel;
import Server.DBWorker;

import java.io.*;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MainServer {


    public static void main(String[] args) {
        Inet4Address ip;
        int port;

        try (ServerSocket server = new ServerSocket(8000))
        {
            System.out.println("Server started");

            DBWorker worker = new DBWorker();

            IDao CD = new CitiesDao();
            CD.getAll();
            CD.get(2);
            CD.update(2);



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
