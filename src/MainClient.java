import Server.Packet;

import java.io.*;
import java.net.Inet4Address;
import java.net.Socket;

public class MainClient {
    public static void main(String[] args) {
        Inet4Address ip;
        int port;

        try {
            Socket socket = new Socket("127.0.0.1", 8000);


            OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream()); // Чек
            ByteArrayOutputStream buffer = new ByteArrayOutputStream(); // Ч

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            DataInputStream dis = new DataInputStream(ois);
            BufferedInputStream bis = new BufferedInputStream(dis);

            ObjectOutputStream ous = new ObjectOutputStream(socket.getOutputStream());
            DataOutputStream dos = new DataOutputStream(ous);
            BufferedOutputStream bos = new BufferedOutputStream(dos);



            BufferedWriter writer =
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    socket.getOutputStream()));
            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    socket.getInputStream()));
            System.out.println("Connected to server");
            String request = "tours "; // Client -> outputStream -> server

            Packet packet = new Packet(1,null);
            //ous.writeObject(packet); // Только в ous есть write object
            //ous.flush();
            writer.write(request);
            writer.newLine();
            writer.flush();

            String response = reader.readLine(); // Server -> inputStream ->
            System.out.println("Response: " + response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
