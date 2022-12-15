import java.io.*;
import java.net.Inet4Address;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainClient {
    public static void main(String[] args) {
        Inet4Address ip;
        int port;

        try {
            Socket socket = new Socket("127.0.0.1", 8000);
            BufferedWriter writer =
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    socket.getOutputStream()));
            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    socket.getInputStream()));
            System.out.println("Connected to server");
            String request = "Huge cocks"; // Client -> outputStream -> server
            writer.write(request);
            writer.newLine();
            writer.flush();

            String response = reader.readLine(); // Server -> inputStream -> server
            System.out.println("Response: " + response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
