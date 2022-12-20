import Entity.Tours;
import MVC.JMainFrame;
import Server.Tours.ToursView;


import javax.swing.plaf.nimbus.State;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class Main {
    private static final String URL = "jdbc:mysql://192.168.192:3306/kursa";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "9228lalala";
    public static void main(String[] args)  {
        DBWorker worker = new DBWorker();

        String query = "select * from tours";
        try {
            Statement statement = worker.getConnection().createStatement();
            ResultSet set = statement.executeQuery(query);

            while (set.next()){
                Tours tour = new Tours();
                tour.setId(set.getInt(1));
                tour.setCity(set.getString(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}