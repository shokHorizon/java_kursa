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
            //TD.get(1);
            IDao TRD = new TripsDao();
            System.out.println();
            //TRD.getAll();
            TRD.get(1);
            System.out.println();
            TRD.delete(2);
            TRD.getAll();
            System.out.println();
            //TRD.get(1);



    }

}