package Server.Tours;

import MVC.DAO.ToursDao;
import MVC.Model;
import MVC.Models.ToursModel;
import Protocols.Packet;
import MVC.IController;

import java.util.List;
import java.util.Optional;

public class ToursController implements IController {

    public static final ToursController INSTANCE = new ToursController();

    @Override
    public Packet process(Packet tours) {
        return new Packet(null, new ToursDao().getAll());
    }

    public void getTours(){
        List<ToursModel> toursModels = new ToursDao().getAll();
        ToursView.update(toursModels);
    }

    public boolean getTour(int id){
        Optional<ToursModel> toursModel = new ToursDao().get(id);
        return toursModel.isPresent();
    }

    public void insertTour(ToursModel model){
        new ToursDao().save(model);
    }

    public void deleteTour(int id){
        new ToursDao().delete(id);
    }

    public void updateTour(ToursModel model){
        new ToursDao().update(model);
    }
}
