package MVC.DAO;

import Entity.Tickets;
import MVC.IDao;
import Server.DBWorker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class TicketsDao implements IDao {

    String Tname = "tickets";
    @Override
    public Optional get(long id) {
        return Optional.empty();
    }

    @Override
    public List getAll() {
        try {
            Statement statement = DBWorker.INSTANCE.getConnection().createStatement();
            String query = "select * from tickets";
            ResultSet set = statement.executeQuery(query);
            while (set.next()){
                Tickets tick = new Tickets();
                tick.setId(set.getInt("id"));
                tick.setName(set.getString("name"));
                tick.setTrip(set.getInt("trip"));
                System.out.println(tick);}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    } // select * from tickets;

    @Override
    public void save(Object o) {

    }

    @Override
    public void update(Object o, String[] params) {

    }

    @Override
    public void delete(Object o) {

    }
}
