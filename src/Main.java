import MVC.JMainFrame;
import Server.Tours.ToursView;


import javax.swing.plaf.nimbus.State;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/kursa";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "9228lalala";
    public static void main(String[] args)  {
        DBWorker worker = new DBWorker();


    }

}