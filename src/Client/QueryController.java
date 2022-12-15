package Client;

import Server.Tours.ToursController;

import Protocols.Packet;

public class QueryController {
    static Packet query_request(Packet packet){
        switch (Packet.CODES.get(packet.code).toLowerCase()){
            case "tours"->{
                Packet packet_return = ToursController.INSTANCE.process(packet);
                packet_return.code = packet.code;
                return packet;
            }
        }
        return null;
    }
}
