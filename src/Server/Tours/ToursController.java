package Server.Tours;

import MVC.IController;
import MVC.IModel;

import java.util.stream.Stream;

public class ToursController implements IController {

    public static final ToursController INSTANCE = new ToursController();

    @Override
    public IModel[] process(IModel[] tours) {
        return null;
    }
}
