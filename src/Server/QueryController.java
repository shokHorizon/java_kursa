package Server;

import Server.Tours.ToursController;

public class QueryController {
    static Packet query_request(Packet packet){
        switch (Packet.CODES.get(packet.code).toLowerCase()){
            case "tours"->{
                return ToursController.INSTANCE.process(packet);
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
