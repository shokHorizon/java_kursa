package Client;

import Server.Tours.ToursController;

import Protocols.Packet;

public class QueryController {
    static Packet query_request(Packet packet){
        switch (Packet.CODES.get(packet.getCode()).toLowerCase()){
            case "tours"->{
                Packet packet_return = ToursController.INSTANCE.process(packet);
                packet_return.setCode(packet.getCode());
                return packet;
            }
        }
        return null;
    }
}
