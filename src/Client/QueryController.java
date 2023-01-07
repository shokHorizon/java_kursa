package Client;


import Models.Model;
import Models.Users;
import Protocols.Packet;

public class QueryController {
    public static Model query_request(Packet packet) {
        switch (packet.getQueryModel()) {
            case Users -> {
                return new Users(0, "asd", 0000, 1); // socketClient.sendPacket(packet)
            }
            case Books -> {
                return null;
            }
            case Cities -> {
                return null;
            }
            case Travels -> {
                return null;
            }
            case Countries -> {
                return null;
            }
            case TravelTypes -> {
                return null;
            }
        }
        return null;
    }
}
