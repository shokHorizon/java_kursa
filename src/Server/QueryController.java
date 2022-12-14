package Server;

import MVC.IController;
import Server.Tours.ToursController;

import java.util.stream.Stream;

public class QueryController {
    static IController get_controller(String status, Stream stream){
        switch (status){
            case "TOUR"->{
                return Parser.parse(new ToursController());
            }
            case "TRIP"->{
                return Parser.parse(new TripsController());
            }
            case "TICK"->{
                return Parser.parse(new TicketController(Parser.parse(stream)));
            }
        }
    }
}
