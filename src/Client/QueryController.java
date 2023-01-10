package Client;


import Models.Model;
import Models.Users;
import Protocols.Packet;

import java.util.List;

public class QueryController {
    public static List<? extends Model> query_request(Packet<?> packet) {
        switch (packet.getQueryModel()) {
            case Users, Books, TravelTypes, Countries, Travels, Cities -> {
                Packet received_packet = SocketClient.sendPacket(packet);
                System.out.println("Модель от приложения: " + packet.getModels());
                return received_packet.getModels();
            }
        }
        return null;
    }
}
