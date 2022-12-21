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



    }

}