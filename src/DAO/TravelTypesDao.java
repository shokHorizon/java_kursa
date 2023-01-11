package DAO;


import Models.TravelTypes;
import Models.Users;
import Server.DBWorker;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static Client.SocketClient.log;
import static Client.SocketClient.logWarning;


public class TravelTypesDao implements IDao<TravelTypes> {

    public static final TravelTypesDao INSTANCE = new TravelTypesDao();
    @Override
    public LinkedList<TravelTypes> get(TravelTypes travelTypes) {
        String query = "select * from travelTypes";
        StringBuilder sb = new StringBuilder();
        LinkedList<String> parameters = new LinkedList<>();
        LinkedList <TravelTypes> listTravelTypes = new LinkedList<>();
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
        ResultSet set;
        try {

            PreparedStatement preparedStatement = DBWorker.INSTANCE.prepareStatement(query);
            //preparedStatement.setInt(1,id);
            set = preparedStatement.executeQuery(); // В save - аналог
            travelTypes = new TravelTypes(
                    set.getInt("id"),
                    set.getString("name")
            );
            //System.out.println(travelTypes);

        } catch (SQLException e) {
            log.warning("Произошёл таймаут бездействия в базе данных.");
            //throw new RuntimeException(e);
        } return listTravelTypes;
    }

    @Override
    public List<TravelTypes> getAll() {
        List<TravelTypes> travelTypesList = new LinkedList<>();
        try {
            Statement statement = DBWorker.INSTANCE.createStatement();
            String query = "select * from travelTypes";
            ResultSet set = statement.executeQuery(query);
            while (set.next()){
                TravelTypes travelTypes = new TravelTypes(
                        set.getInt("id"),
                        set.getString("name")
                );
                //System.out.println(travelTypes);
                travelTypesList.add(travelTypes);
            }
        } catch (SQLException e) {
            log.warning("Произошёл таймаут бездействия в базе данных.");
            //throw new RuntimeException(e);
        } return travelTypesList;
    }

    @Override
    public boolean save(TravelTypes travelTypesModel) { // insert
        try {
            String query = "insert into travelTypes values (?,?)";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.prepareStatement(query);
            preparedStatement.setInt(1,travelTypesModel.getId());
            preparedStatement.setString(2,travelTypesModel.getName());

            preparedStatement.execute();
        } catch (SQLException e) {
            log.warning("Произошёл таймаут бездействия в базе данных.");
            return false;
        }
        return true;
    }

    @Override
    public boolean update(TravelTypes travelTypesModel) {
        try {
            String query = "update travelTypes set name = ? where id = ?";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.prepareStatement(query);
            preparedStatement.setString(1,travelTypesModel.getName()); // Будет меняться на одно и то же, поэтому надо замену придумать
            preparedStatement.setInt(2,travelTypesModel.getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            log.warning("Произошёл таймаут бездействия в базе данных.");
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(int id) {
        try {
            String query = "delete from travelTypes where id = ?";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.prepareStatement(query);
            preparedStatement.setInt(1,id); // Будет меняться на одно и то же, поэтому надо замену придумать
            preparedStatement.execute();

        } catch (SQLException e) {
            log.warning("Произошёл таймаут бездействия в базе данных.");
            return false;
        }
        return true;

        //travelTypesModels.remove(travelTypes); Снос списка
    }
}
