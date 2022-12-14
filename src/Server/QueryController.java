package Server;

import MVC.IController;
import Server.Tours.ToursController;

public class QueryController {
    static IController get_controller(String status){
        switch (status){
            case "TOUR"->{
                return ToursController.class;
            }

        }
    }
}
