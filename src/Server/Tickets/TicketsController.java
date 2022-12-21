package Server.Tickets;

import MVC.DAO.TicketsDao;
import MVC.DAO.ToursDao;
import MVC.IController;
import MVC.IModel;
import MVC.Models.TicketsModel;
import MVC.Models.ToursModel;
import Protocols.Packet;
import Server.Tours.ToursController;
import Server.Tours.ToursView;

import java.util.List;
import java.util.Optional;

public class TicketsController implements IController {

    public static final TicketsController INSTANCE = new TicketsController();

    @Override
    public Packet process(Packet tickets) {
        for (Object model: tickets.getModels()){
            if (model instanceof TicketsModel ticketsModel){
                new TicketsDao().save(ticketsModel);
            }
        }
        return new Packet(null);
    }

    public void getTours(){
        List<ToursModel> toursModels = new ToursDao().getAll();
        ToursView.update(toursModels);
    }

    public boolean getTour(int id){
        Optional<ToursModel> toursModel = new ToursDao().get(id);
        return toursModel.isPresent();
    }

    public boolean insertTour(ToursModel model){

        if (model.save() == null)
            return false;
        return true;
    }

    public boolean deleteTour(int id){
        if (ToursModel.objects.delete(id) == null)
            return false;
        return true;
    }

    public boolean updateTour(ToursModel model){
        if (ToursModel.objects.update(model) == null)
            return false;
        return true;
    }
}
