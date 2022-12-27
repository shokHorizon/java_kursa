package MVC.DAO;

import MVC.IDao;
import MVC.Models.TripsModel;
import Server.DBWorker;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class TripsDao implements IDao<TripsModel> {

    public static final TripsDao INSTANCE = new TripsDao();
    @Override
    public Optional<TripsModel> get(int id) {
        try {
            String query = "select * from trips where id = ?";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,id);
            Statement st = preparedStatement;
            TripsModel trips = new TripsModel();


            ResultSet set = preparedStatement.executeQuery(query); // В save - аналог
            TripsModel tripsModel = new TripsModel(
                    set.getInt("id"),
                    set.getInt("tour"),
                    set.getInt("price"),
                    set.getTimestamp("date"));
            System.out.println(trips);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } return null;
    }

    @Override
    public List<TripsModel> getAll() {
        try {
            Statement statement = DBWorker.INSTANCE.getConnection().createStatement();
            String query = "select * from trips";
            ResultSet set = statement.executeQuery(query);
            while (set.next()){
                TripsModel trip = new TripsModel(
                        set.getInt("id"),
                        set.getInt("tour"),
                        set.getInt("price"),
                        set.getTimestamp("date"));
                System.out.println(trip);}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void save(TripsModel tripsModel) {
        try {
            String query = "insert into trips values (?,?,?,?)";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,tripsModel.getId());
            preparedStatement.setInt(2,tripsModel.getTour());
            preparedStatement.setInt(3,tripsModel.getPrice());
            preparedStatement.setTimestamp(4,tripsModel.getDate());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return;

    }

    @Override
    public void update(TripsModel tripsModel) {
        try {
            String query = "update Trips set tour = ?, price = ?, date = ? where id = ?";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,tripsModel.getTour()); // Будет меняться на одно и то же, поэтому надо замену придумать
            preparedStatement.setInt(2,tripsModel.getPrice());
            preparedStatement.setTimestamp(3,tripsModel.getDate());
            preparedStatement.setInt(4,tripsModel.getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return;
    }

    @Override
    public void delete(int id) {
        try{
            String query = "delete from trips where id = ?";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,id); // Будет меняться на одно и то же, поэтому надо замену придумать
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return;
    }
}
