package Server.Tours;

import MVC.Models.ToursModel;
import Protocols.Packet;
import MVC.IController;

public class ToursController implements IController {

    public static final ToursController INSTANCE = new ToursController();

    @Override
    public Packet process(Packet tours) {
        return new Packet(1, null);
    }

    public void getTours(){
        ToursModel[] toursModels = ToursModel.objects.all();
        ToursView.update(toursModels);
    }

    public boolean getTour(int id){
        ToursModel toursModel = ToursModel.objects.get(id);
        if (toursModel == null)
            return false;
        return true;
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
