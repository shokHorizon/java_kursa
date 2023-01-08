package DAO;


import Models.TravelTypes;
import Server.DBWorker;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;



public class TravelTypesDao implements IDao<TravelTypes> {

    public static final TravelTypesDao INSTANCE = new TravelTypesDao();
    @Override
    public Optional<TravelTypes> get(TravelTypes travelTypes) {
        String query = "select * from travelTypes";
        StringBuilder sb = new StringBuilder();
        LinkedList<String> parameters = new LinkedList<>();
        if (travelTypes != null)
        {
            if (travelTypes.getId() > 0)
                parameters.add(" id = " + travelTypes.getId());
            if (travelTypes.getName() != null)
                parameters.add(" name = " + travelTypes.getName());
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
            travelTypes = new TravelTypes(
                    set.getInt("id"),
                    set.getString("name")
            );
            System.out.println(travelTypes);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } return Optional.of(travelTypes);
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
