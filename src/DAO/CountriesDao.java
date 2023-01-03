package DAO;

import Models.Countries;
import Server.DBWorker;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class CountriesDao implements IDao<Countries> {

    public static final CountriesDao INSTANCE = new CountriesDao();
    @Override
    public Optional<Countries> get(int id) {

        try {
            String query = "select * from countries where id = ?";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet set = preparedStatement.executeQuery(query); // В save - аналог
            Countries countries = new Countries(
                    set.getInt("id"),
                    set.getString("name")
            );
            System.out.println(countries);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } return null;
    }

    @Override
    public List<Countries> getAll() {

        try {
            Statement statement = DBWorker.INSTANCE.getConnection().createStatement();
            String query = "select * from countries";
            ResultSet set = statement.executeQuery(query);
            while (set.next()){
                Countries countries = new Countries(
                        set.getInt("id"),
                        set.getString("name")
                );
                System.out.println(countries);}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } return null;
    }

    @Override
    public void save(Countries countriesModel) { // insert
        try {
            String query = "insert into countries values (?,?,?)";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,countriesModel.getId());
            preparedStatement.setString(2,countriesModel.getName());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return;
    }

    @Override
    public void update(Countries countriesModel) {
        try {
            String query = "update countries set name = ? where id = ?";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.getConnection().prepareStatement(query);
            preparedStatement.setString(1,countriesModel.getName()); // Будет меняться на одно и то же, поэтому надо замену придумать
            preparedStatement.setInt(2,countriesModel.getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return;
    }

    @Override
    public void delete(int id) {
        try {
            String query = "delete from countries where id = ?";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,id); // Будет меняться на одно и то же, поэтому надо замену придумать
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return;

        //countriesModels.remove(countries); Снос списка
    }

}
