package DAO;


import Models.TravelTypes;
import Server.DBWorker;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;



public class TravelTypesDao implements IDao<TravelTypes> {


    public static final TravelTypesDao INSTANCE = new TravelTypesDao();
    @Override
    public Optional<TravelTypes> get(int id) {

        try {
            String query = "select * from travelTypes where id = ?";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet set = preparedStatement.executeQuery(); // В save - аналог
            TravelTypes travelTypes = new TravelTypes(
                    set.getInt("id"),
                    set.getString("name")
            );
            System.out.println(travelTypes);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } return null;
    }

    @Override
    public List<TravelTypes> getAll() {

        try {
            Statement statement = DBWorker.INSTANCE.getConnection().createStatement();
            String query = "select * from travelTypes";
            ResultSet set = statement.executeQuery(query);
            while (set.next()){
                TravelTypes travelTypes = new TravelTypes(
                        set.getInt("id"),
                        set.getString("name")
                );
                System.out.println(travelTypes);}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } return null;
    }

    @Override
    public void save(TravelTypes travelTypesModel) { // insert
        try {
            String query = "insert into travelTypes values (?,?)";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,travelTypesModel.getId());
            preparedStatement.setString(2,travelTypesModel.getName());

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return;
    }

    @Override
    public void update(TravelTypes travelTypesModel) {
        try {
            String query = "update travelTypes set name = ? where id = ?";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.getConnection().prepareStatement(query);
            preparedStatement.setString(1,travelTypesModel.getName()); // Будет меняться на одно и то же, поэтому надо замену придумать
            preparedStatement.setInt(2,travelTypesModel.getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return;
    }

    @Override
    public void delete(int id) {
        try {
            String query = "delete from travelTypes where id = ?";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,id); // Будет меняться на одно и то же, поэтому надо замену придумать
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return;

        //travelTypesModels.remove(travelTypes); Снос списка
    }
}
