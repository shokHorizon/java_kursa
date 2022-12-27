package Server;


import Protocols.Packet;
import Server.Tours.ToursController;

public class QueryController {
    static Packet query_request(Packet packet){
        switch (Packet.CODES.get(packet.getCode()).toLowerCase()){
            case "tours"->{
                Packet packet_return = ToursController.INSTANCE.process(packet);
                packet_return.setCode(packet.getCode());
                return packet;
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
