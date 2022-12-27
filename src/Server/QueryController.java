package Server;


import Protocols.Packet;

public class QueryController {
    static Packet query_request(Packet packet){
        switch (Packet.CODES.get(packet.getCode()).toLowerCase()){
            case "tours"->{
                return null;
            }
            case "trips"->{
                return null;
            }
            case "ticket"->{
                return null;
            }
        }
        return null;
    }
}
