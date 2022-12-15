package Server.Tours;

import MVC.IController;
import MVC.IModel;
import Server.Packet;

import java.util.stream.Stream;

public class ToursController implements IController {

    public static final ToursController INSTANCE = new ToursController();

    @Override
    public Packet process(Packet tours) {
        return null;
    }
}
