package Server.Tours;

import Protocols.Packet;
import MVC.IController;

public class ToursController implements IController {

    public static final ToursController INSTANCE = new ToursController();

    @Override
    public Packet process(Packet tours) {
        return new Packet(1, null);
    }


}
