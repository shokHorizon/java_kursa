package Server;

import Server.Trips.TripsModel;

import java.util.stream.Stream;

public class Parser {

    static public Server.Tickets.Model parse(Stream stream){
        return Server.Tickets.Model;
    }

    static public Stream parse(TripsModel[] trips){
        return Stream;
    }

    static public Stream parse(Server.Tours.Model[] tours){
        return Stream;
    }
}
