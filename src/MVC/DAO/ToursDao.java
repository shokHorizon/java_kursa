package MVC.DAO;


import MVC.IDao;
import MVC.Models.ToursModel;
import Server.DBWorker;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ToursDao implements IDao<ToursModel> {

    public static final ToursDao INSTANCE = new ToursDao();
    @Override
    public Optional<ToursModel> get(int id) {

        try {
            String query = "select * from tours where id = ?";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet set = preparedStatement.executeQuery(query); // В save - аналог
            ToursModel tours = new ToursModel(
                    set.getInt("id"),
                    set.getString("city"));
            System.out.println(tours);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } return null;
    }

    @Override
    public List<ToursModel> getAll() {

        try {
            Statement statement = DBWorker.INSTANCE.getConnection().createStatement();
            String query = "select * from tours";
            ResultSet set = statement.executeQuery(query);
            while (set.next()){
                ToursModel tours = new ToursModel(
                set.getInt("id"),
                set.getString("city"));
                System.out.println(tours);}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } return null;
    }

    @Override
    public void save(ToursModel toursModel) { // insert
        try {
            String query = "insert into tours values (?,?)";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,toursModel.getId());
            preparedStatement.setString(2,toursModel.getCity());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return;
    }

    @Override
    public void update(ToursModel toursModel) {
        try {
            String query = "update tours set city = ? where id = ?";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.getConnection().prepareStatement(query);
            preparedStatement.setString(1,toursModel.getCity()); // Будет меняться на одно и то же, поэтому надо замену придумать
            preparedStatement.setInt(2,toursModel.getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return;
    }

    @Override
    public void delete(int id) {
        try {
            String query = "delete from tours where id = ?";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,id); // Будет меняться на одно и то же, поэтому надо замену придумать
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return;

        //toursModels.remove(toursModel); Снос списка
    }
}
