package Client;


import Models.Model;
import Models.Users;
import Protocols.Packet;

public class QueryController {
    public static Model query_request(Packet<?> packet) {
        switch (packet.getQueryModel()) {
            case Users -> {
                return (Model) SocketClient.INSTANCE.sendPacket(packet).getModels().get(0);
            }
            case Books, TravelTypes, Countries, Travels, Cities -> {
                return (Model) SocketClient.INSTANCE.sendPacket(packet).getModels();
            }
        }
        return null;
    }
}
