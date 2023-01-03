package Server;

import java.sql.*;


public class DBWorker {

    public static final DBWorker INSTANCE = new DBWorker();
    private Connection connection;
    private static final String URL = "jdbc:mysql://sql.freedb.tech:3306/freedb_kursa_java";
    private static final String USERNAME = "freedb_root_name1"; // root
    private static final String PASSWORD = "EDXJd!v!SAY8%d7";

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
