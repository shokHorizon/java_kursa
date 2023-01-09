package DAO;

import Models.Countries;
import Models.Users;
import Server.DBWorker;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class CountriesDao implements IDao<Countries> {

    public static final CountriesDao INSTANCE = new CountriesDao();
    @Override
    public LinkedList<Countries> get(Countries countries) {
        String query = "select * from countries";
        StringBuilder sb = new StringBuilder();
        LinkedList<String> parameters = new LinkedList<>();
        LinkedList <Countries> listCountries = new LinkedList<>();
        if (countries != null)
        {
            if (countries.getId() > 0)
                parameters.add(" id = " + countries.getId());
            if (countries.getName() != null)
                parameters.add(" name = " + countries.getName());
            if (parameters.size() > 0) {
                sb.append(" where");
                for (String str: parameters)
                    sb.append(str).append(" and");
                sb.delete(sb.length()-5,sb.length()-1);
                query += sb.toString();
            }
        }
        ResultSet set;
        try {

            PreparedStatement preparedStatement = DBWorker.INSTANCE.prepareStatement(query);
            //preparedStatement.setInt(1,id);
            set = preparedStatement.executeQuery(); // В save - аналог
            countries = new Countries(
                    set.getInt("id"),
                    set.getString("name")
            );
            System.out.println(countries);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } return listCountries;
    }

    @Override
    public List<Countries> getAll() {
        List<Countries> countriesList = new LinkedList<>();
        try {
            Statement statement = DBWorker.INSTANCE.createStatement();
            String query = "select * from countries";
            ResultSet set = statement.executeQuery(query);
            while (set.next()){
                Countries countries = new Countries(
                        set.getInt("id"),
                        set.getString("name")
                );
                System.out.println(countries);
                countriesList.add(countries);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } return countriesList;
    }

    @Override
    public void save(Countries countriesModel) { // insert
        try {
            String query = "insert into countries values (?,?,?)";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.prepareStatement(query);
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
            PreparedStatement preparedStatement = DBWorker.INSTANCE.prepareStatement(query);
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
            PreparedStatement preparedStatement = DBWorker.INSTANCE.prepareStatement(query);
            preparedStatement.setInt(1,id); // Будет меняться на одно и то же, поэтому надо замену придумать
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return;

        //countriesModels.remove(countries); Снос списка
    }

}
