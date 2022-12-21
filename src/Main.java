import Entity.Tickets;
import MVC.DAO.TicketsDao;
import MVC.DAO.TripsDao;
import MVC.IDao;
import Server.DBWorker;


import java.sql.*;

public class Main {
    private static final String URL = "jdbc:mysql://192.168.192:3306/kursa";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "9228lalala";
    public static void main(String[] args)  {
        DBWorker worker = new DBWorker();

//        try {
            //Statement statement = worker.getConnection().createStatement();
            //ResultSet set = statement.executeQuery(query);
            IDao TD = new TicketsDao();
            TD.getAll();
            IDao TRD = new TripsDao();
            System.out.println();
            TRD.getAll();
            TRD.delete(3);
            TRD.getAll();



    }

}