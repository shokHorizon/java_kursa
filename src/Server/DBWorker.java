package Server;

import java.sql.*;
import java.util.Optional;
import java.util.Properties;


public class DBWorker {

    public static final DBWorker INSTANCE = new DBWorker();
    private Connection connection;
    private static final String URL = "jdbc:mysql://sql.freedb.tech:3306/freedb_kursa_java";
    private static final String USERNAME = "freedb_impostor"; // root
    private static final String PASSWORD = "PVSuZx7b8PYA%a5";



    Properties connectionsProps = new Properties();

    public Statement createStatement() {
        Statement statement = null;
        while (statement == null)
        {
            try {
                statement = connection.createStatement();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return statement;
    }

    public PreparedStatement prepareStatement(String query) {
        PreparedStatement statement = null;
        while (statement == null)
        {
            try {
                statement = connection.prepareStatement(query);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return statement;
    }


    public DBWorker()  {
            connectionsProps.put("autoReconnect","true");
            connectionsProps.put("maxReconnects","3");
            connectionsProps.put("user", USERNAME);
            connectionsProps.put("password", PASSWORD);
            //JMainFrame jMainFrame = new JMainFrame();
            //jMainFrame.set_panel(ToursView.INSTANCE);

            try {
                connection = DriverManager.getConnection(URL,connectionsProps);
                System.out.println("Соединение установлено");
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
}
