import Protocols.Packet;
import Server.AppController;
import Server.SocketServer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;



public class MainServer extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Server/serverApp.fxml"));
        primaryStage.setTitle("Server");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
        Inet4Address ip;
        int port;

        try (ServerSocket server = new ServerSocket(8000))
        {
            System.out.println("Server started");

            while (true)
            try {
                Socket socket = server.accept(); // сокет под сервак

                ObjectOutputStream ous = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

                Packet response = (Packet) ois.readObject();
                response.Print();
                response.setCode(228);
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
