package DAO;

import Models.Books;
import Models.Cities;
import Server.DBWorker;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class CitiesDao implements IDao<Cities> {
    
    public static final CitiesDao INSTANCE = new CitiesDao();
    @Override
    public Optional<Cities> get(Cities cities) {
        String query = "select * from cities";
        StringBuilder sb = new StringBuilder();
        LinkedList<String> parameters = new LinkedList<>();
        if (cities != null)
        {
            if (cities.getId() > 0)
                parameters.add(" id = " + cities.getId());
            if (cities.getName() != null)
                parameters.add(" name = " + cities.getName());
            if (cities.getCountry() > 0)
                parameters.add(" country = " + cities.getCountry());
            if (parameters.size() > 0) {
                sb.append(" where");
                for (String str: parameters)
                    sb.append(str).append(" and");
                sb.delete(sb.length()-5,sb.length()-1);
                query += sb.toString();
            }
        }
        try {

            PreparedStatement preparedStatement = DBWorker.INSTANCE.getConnection().prepareStatement(query);
            //preparedStatement.setInt(1,id);
            ResultSet set = preparedStatement.executeQuery(); // В save - аналог
            set.next();
            cities = new Cities(
                    set.getInt("id"),
                    set.getString("name"),
                    set.getInt("country")
            );
            System.out.println(cities);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } return Optional.of(cities);
    }

    @Override
    public List<Cities> getAll() {

        try {
            Statement statement = DBWorker.INSTANCE.getConnection().createStatement();
            String query = "select * from cities";
            ResultSet set = statement.executeQuery(query);
            while (set.next()){
                Cities cities = new Cities(
                        set.getInt("id"),
                        set.getString("name"),
                        set.getInt("country")
                );
                System.out.println(cities);}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } return null;
    }

    @Override
    public void save(Cities citiesModel) { // insert
        try {
            String query = "insert into cities values (?,?,?)";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,citiesModel.getId());
            preparedStatement.setString(2,citiesModel.getName());
            preparedStatement.setInt(3,citiesModel.getCountry());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return;
    }

    @Override
    public void update(Cities citiesModel) {
        try {
            String query = "update cities set name = ?, country = ? where id = ?";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.getConnection().prepareStatement(query);
            preparedStatement.setString(1,citiesModel.getName()); // Будет меняться на одно и то же, поэтому надо замену придумать
            preparedStatement.setInt(2,citiesModel.getCountry());
            preparedStatement.setInt(3,citiesModel.getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return;
    }

    @Override
    public void delete(int id) {
        try {
            String query = "delete from cities where id = ?";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,id); // Будет меняться на одно и то же, поэтому надо замену придумать
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return;

        //citiesModels.remove(cities); Снос списка
    }
}
