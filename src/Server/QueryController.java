package Server;


import Models.Model;
import Protocols.Packet;

public class QueryController {
    static Packet<Model> query_request(Packet<Model> packet){
        switch (packet.getQueryModel()){
            case Users->{
                return null;
            }
            case Books->{
                return null;
            }
            case Countries->{
                return null;
            }
            case Travels->{
                return null;
            }
            case TravelTypes->{
                return null;
            }
            case Cities->{
                return null;
            }
        }
        return null;
    }
}
