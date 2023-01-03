package DAO;

import Models.Travels;
import Server.DBWorker;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class TravelsDao implements IDao<Travels>{

    public static final TravelsDao INSTANCE = new TravelsDao();
    @Override
    public Optional<Travels> get(int id) {

        try {
            String query = "select * from travels where id = ?";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet set = preparedStatement.executeQuery(query); // В save - аналог
            Travels travels = new Travels(
                    set.getInt("id"),
                    set.getInt("type"),
                    set.getString("name"),
                    set.getInt("city"),
                    set.getString("image"),
                    set.getString("coords"),
                    set.getInt("price"),
                    set.getString("name")
            );
            System.out.println(travels);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } return null;
    }

    @Override
    public List<Travels> getAll() {

        try {
            Statement statement = DBWorker.INSTANCE.getConnection().createStatement();
            String query = "select * from travels";
            ResultSet set = statement.executeQuery(query);
            while (set.next()){
                Travels travels = new Travels(
                        set.getInt("id"),
                        set.getInt("type"),
                        set.getString("name"),
                        set.getInt("city"),
                        set.getString("image"),
                        set.getString("coords"),
                        set.getInt("price"),
                        set.getString("name")
                );
                System.out.println(travels);}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } return null;
    }

    @Override
    public void save(Travels travelsModel) { // insert
        try {
            String query = "insert into travels values (?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,travelsModel.getId());
            preparedStatement.setString(2,travelsModel.getName());
            preparedStatement.setString(2,travelsModel.getName());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return;
    }

    @Override
    public void update(Travels travelsModel) {
        try {
            String query = "update travels set type = ?, name = ?, city = ?, image = ?, coords = ?, price = ?, supplier = ? where id = ?";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,travelsModel.getType()); // Будет меняться на одно и то же, поэтому надо замену придумать
            preparedStatement.setString(2,travelsModel.getName());
            preparedStatement.setInt(3,travelsModel.getCity());
            preparedStatement.setString(4,travelsModel.getImage());
            preparedStatement.setString(5,travelsModel.getCoordinates());
            preparedStatement.setInt(6,travelsModel.getPrice());
            preparedStatement.setString(7,travelsModel.getSupplier());
            preparedStatement.setInt(8,travelsModel.getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return;
    }

    @Override
    public void delete(int id) {
        try {
            String query = "delete from travels where id = ?";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,id); // Будет меняться на одно и то же, поэтому надо замену придумать
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return;

        //travelsModels.remove(travels); Снос списка
    }


}