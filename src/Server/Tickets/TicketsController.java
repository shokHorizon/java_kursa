package Server.Tickets;

import MVC.DAO.TicketsDao;
import MVC.IController;
import MVC.Models.TicketsModel;
import MVC.Models.ToursModel;
import Protocols.Packet;

import java.util.List;
import java.util.Optional;

public class TicketsController implements IController <TicketsModel>{

    public static final TicketsController INSTANCE = new TicketsController();

    @Override
    public Packet<TicketsModel> process(Packet<TicketsModel> tickets) {
        for (Object model: tickets.getModels()){
            if (model instanceof TicketsModel ticketsModel){
                new TicketsDao().save(ticketsModel);
            }
        }
        return new Packet<TicketsModel>();
    }

    public void getTickets(){
        List<TicketsModel> ticketsModels = new TicketsDao().getAll();
        //ToursView.update(toursModels);
    }

    public void getTicket(int id){
        Optional<TicketsModel> ticketsModel = new TicketsDao().get(id);
    }

    public boolean insertTicket(ToursModel model){
        new TicketsDao().save(model);
    }

    public boolean deleteTicket(int id){
        new TicketsDao().delete(id);
    }

    public boolean updateTicket(ToursModel model){
        new TicketsDao().update(model);
    }
}
