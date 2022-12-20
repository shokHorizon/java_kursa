import Entity.Tickets;
import MVC.DAO.TicketsDao;
import MVC.IDao;
import Server.DBWorker;


import java.sql.*;

public class Main {
    private static final String URL = "jdbc:mysql://192.168.192:3306/kursa";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "9228lalala";
    public static void main(String[] args)  {
        DBWorker worker = new DBWorker();

        String query = "select * from tickets";
//        try {
            //Statement statement = worker.getConnection().createStatement();
            //ResultSet set = statement.executeQuery(query);
            IDao TD = new TicketsDao();
            TD.getAll();

//            while (set.next()){
//                Tickets tick = new Tickets();
//                tick.setId(set.getInt("id"));
//                tick.setName(set.getString("name"));
//                tick.setTrip(set.getInt("trip"));
//                System.out.println(tick);
//            }
//                while (set.next()){
//                    Trips trip = new Trips();
//                    trip.setId(set.getInt("id"));
//                    trip.setTour(set.getInt("tour"));
//                    trip.setPrice(set.getInt("price"));
//                    trip.setDate(set.getTimestamp("date"));
//                    System.out.println(trip);
//                }
//            while (set.next()){
//                Tours tour = new Tours();
//                tour.setId(set.getInt("id"));
//                tour.setCity(set.getString("city"));
//                System.out.println(tour);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//          }


    }

}