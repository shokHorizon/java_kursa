package Server.Trips;

import MVC.DAO.TripsDao;
import MVC.IController;
import MVC.Models.TripsModel;
import Protocols.Packet;

public class TripsController implements IController {

    public static final TripsController INSTANCE = new TripsController();

    @Override
    public Packet process(Packet trips) {
        return new Packet(null, new TripsDao().getAll());
    }

    public void getTrips(int tourId){
        TripsModel[] tripsModels = TripsModel.objects.filter(tour=tourId);
        TripsView.update(tripsModels);
    }

    public boolean getTrip(int id){
        TripsModel tripsModel = TripsModel.objects.get(id);
        if (tripsModel == null)
            return false;
        return true;
    }

    public boolean insertTrip(TripsModel model){
        if (model.save() == null)
            return false;
        return true;
    }

    public boolean deleteTrip(int id){
        if (TripsModel.objects.delete(id) == null)
            return false;
        return true;
    }

    public boolean updateTrip(TripsModel model){
        if (TripsModel.objects.update(model) == null)
            return false;
        return true;
    }
}
