import MVC.JMainFrame;
import Server.Tours.ToursView;
import com.mysql.fabric.jdbc.FabricMySQLDriver;


import java.sql.*;

public class Main {
    private static final String URL = "jdbc:mysql://localhost3306/kursa";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "9228lalala";
    public static void main(String[] args) {
        //JMainFrame jMainFrame = new JMainFrame();
        //jMainFrame.set_panel(ToursView.INSTANCE);
        Connection connection;


        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e){
            System.out.println("Не удалось загрузить класс драйера!");
        }
    }

}