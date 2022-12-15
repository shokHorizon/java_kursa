package Server;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Parser {

    // По стримам тогда надо юзать ObjectIn/OutputStream

    static public Server.Tickets.Model parse(Stream stream){
        return Server.Tickets.Model;
    }

    static public Stream parse(Server.Trips.Model[] trips){
        return Stream;
    }

    static public Stream parse(Server.Tours.Model[] tours){
        return Stream;
    }
}
