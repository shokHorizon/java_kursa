package Server;

import java.sql.*;
import java.util.Optional;
import java.util.Properties;

import static Client.SocketClient.log;
import static Client.SocketClient.logInfo;


public class DBWorker {

    public static final DBWorker INSTANCE = new DBWorker();
    private Connection connection;
    private static final String URL = "jdbc:mysql://185.244.172.157:3306/freedb_kursa_java"; // sql.freedb.tech:3306
    private static final String USERNAME = "trofim"; // root
    private static final String PASSWORD = "Penis";



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
                logInfo("Соединение установлено");
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
}
