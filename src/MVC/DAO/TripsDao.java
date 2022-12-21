package MVC.DAO;

import Entity.Trips;
import MVC.IDao;
import MVC.Models.TripsModel;
import Server.DBWorker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class TripsDao implements IDao<TripsModel> {

    public static final TripsDao INSTANCE = new TripsDao();
    @Override
    public Optional<TripsModel> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<TripsModel> getAll() {
        try {
            Statement statement = DBWorker.INSTANCE.getConnection().createStatement();
            String query = "select * from trips";
            ResultSet set = statement.executeQuery(query);
            while (set.next()){
                Trips trip = new Trips();
                trip.setId(set.getInt("id"));
                trip.setTour(set.getInt("tour"));
                trip.setPrice(set.getInt("price"));
                trip.setDate(set.getTimestamp("date"));
                System.out.println(trip);}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void save(TripsModel tripsModel) {

    }

    @Override
    public void update(TripsModel tripsModel) {

    }

    @Override
    public void delete(int id) {

    }
}
