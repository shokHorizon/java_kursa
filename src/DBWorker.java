import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import MVC.JMainFrame;
import Server.Tours.ToursView;



public class DBWorker {
    private Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/kursa";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "9228lalala";

    public Connection getConnection() {
        return connection;
    }

    public DBWorker()  {
            //JMainFrame jMainFrame = new JMainFrame();
            //jMainFrame.set_panel(ToursView.INSTANCE);



            try {
                connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
                System.out.println("Соединение установлено");
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
}
