package Server;

import Server.Tours.ToursController;
import java.util.stream.Stream;

public class QueryController {
    static Stream query_request(String status, Stream stream){
        switch (status){
            case "TOUR"->{
                return Parser.parse(
                        ToursController.INSTANCE.process(
                                Parser.parse(stream)));
            }
            case "TRIP"->{
                return null;
            }
            case "TICK"->{
                return null;
            }
        }
        return null;
    }
}
