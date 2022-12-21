package MVC.DAO;

import Entity.Tickets;
import Entity.Trips;
import MVC.IDao;
import Server.DBWorker;

import javax.swing.plaf.nimbus.State;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class TripsDao implements IDao {
    @Override
    public Optional get(long id) {
        return Optional.empty();
    }

    @Override
    public List getAll() {
        try {
            Statement statement = DBWorker.INSTANCE.getConnection().createStatement();
            String query = "select * from trips";
            ResultSet set = statement.executeQuery(query);
            while (set.next()){
                Trips trip = new Trips();
                trip.setId(set.getInt("id"));
                trip.setTour(set.getInt("name"));
                trip.setPrice(set.getInt("price"));
                trip.setDate(set.getTimestamp("date"));
                System.out.println(trip);}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

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
