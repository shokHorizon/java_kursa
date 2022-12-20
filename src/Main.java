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
        //JMainFrame jMainFrame = new JMainFrame();
        //jMainFrame.set_panel(ToursView.INSTANCE);



            try {
                Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                DriverManager.registerDriver(driver);


//                if (!connection.isClosed()){
//                    System.out.println("Соединение с БД установлено!");
//                }
//                connection.close();
//                if (connection.isClosed()){
//                    System.out.println("Соединение с БД закрыто!");
//                }
            } catch (SQLException | ClassNotFoundException | NoSuchMethodException | InstantiationException |
                     IllegalAccessException | InvocationTargetException e){
                System.out.println("ЖОПА");
            }

        try (Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD); Statement statement = connection.createStatement()) {
            System.out.println(connection.isClosed());
            //statement.execute("insert into tours (id,city) values(9,'Янечорт');");
            int change =  statement.executeUpdate("UPDATE tours SET city='Я чорт' where id > 5"); // Кол-во изменений
            System.out.println(change);
            ResultSet set = statement.executeQuery("SELECT * from tickets");
            System.out.println(set);
            //statement.addBatch(""); // Добавление набора команд (некая транзакция) в batch
            //statement.executeBatch(); // Выполнения набора команд
            statement.clearBatch();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}