package Server.Trips;

import MVC.DAO.TripsDao;
import MVC.IController;
import MVC.Models.TripsModel;
import Protocols.Packet;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TripsController implements IController <TripsModel>{

    public static final TripsController INSTANCE = new TripsController();

    @Override
    public Packet<TripsModel> process(Packet<TripsModel> trips) {
        return new Packet<TripsModel>(0, new TripsDao().getAll());
    }

    public void getTrips(int tourId){
        List<TripsModel> tripsModels = new TripsDao().getAll(tourId);
        //TripsView.update(tripsModels);
    }

    public void getTrip(int id){
        Optional<TripsModel> tripsModel = new TripsDao().get(id);
    }

    public void insertTrip(TripsModel model){
        new TripsDao().save(model);
    }

    public void deleteTrip(int id){
        new TripsDao().delete(id);
    }

    public void updateTrip(TripsModel model){
        new TripsDao().update(model);
    }
}
