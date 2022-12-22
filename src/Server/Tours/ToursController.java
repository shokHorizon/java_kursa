package Server.Tours;

import MVC.DAO.ToursDao;
import MVC.Model;
import MVC.Models.ToursModel;
import Protocols.Packet;
import MVC.IController;

import java.util.List;
import java.util.Optional;

public class ToursController implements IController <ToursModel>{

    public static final ToursController INSTANCE = new ToursController();

    @Override
    public Packet<ToursModel> process(Packet<ToursModel> tours) {
        return new Packet<ToursModel>(0, new ToursDao().getAll());
    }

    public List<ToursModel> getTours(){
       return new ToursDao().getAll();
    }

    public void getTour(int id){
        Optional<ToursModel> toursModel = new ToursDao().get(id);
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
