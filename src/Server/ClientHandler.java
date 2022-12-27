package Server;

import java.io.*;
import java.net.Socket;
import java.util.stream.Stream;

public class ClientHandler implements Runnable{
    private Socket client;
    private DataInputStream in;
    private DataOutputStream out;
    public ClientHandler(Socket client) throws IOException {
        this.client = client;
        in = new DataInputStream(client.getInputStream());
        out = new DataOutputStream(client.getOutputStream());
    }

    @Override
    public void run() {
        try{
            while (true){
                int len = in.readInt();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < len; i++)
                    sb.append(in.readChar());
                //IController controller = QueryController.get_controller(sb.toString());
                //Stream stream = controller.process();
            }
        } catch (IOException ignore) {}
    }
}
