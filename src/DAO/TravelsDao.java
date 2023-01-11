package DAO;

import Models.Travels;
import Models.TravelsRepr;
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

public class TravelsDao implements IDao<Travels>{

    public static final TravelsDao INSTANCE = new TravelsDao();
    @Override
    public LinkedList<Travels> get(Travels travels) {
        String query = "select * from travels";
        StringBuilder sb = new StringBuilder();
        LinkedList<String> parameters = new LinkedList<>();
        LinkedList <Travels> listTravels = new LinkedList<>();
        if (travels != null)
        {
            if (travels.getId() > 0)
                parameters.add(" id = " + travels.getId());
            if (travels.getType() > 0)
                parameters.add(" type = " + travels.getType());
            if (travels.getName() != null)
                parameters.add(" name = " + travels.getName());
            if (travels.getCity() > 0)
                parameters.add(" city = " + travels.getCity());
            if (travels.getImage() != null)
                parameters.add(" image = " + travels.getImage());
            if (travels.getCoordinates() != null)
                parameters.add(" coords = " + travels.getCoordinates());
            if (travels.getPrice() > 0)
                parameters.add(" price = " + travels.getPrice());
            if (travels.getSupplier() != 0)
                parameters.add(" supplier = " + travels.getSupplier());

            if (parameters.size() > 0) {
                sb.append(" where");
                for (String str: parameters)
                    sb.append(str).append(" and");
                sb.delete(sb.length()-4,sb.length());
                query += sb.toString();
            }
            //System.out.println(query);
        }
        ResultSet set;
        try {

            PreparedStatement preparedStatement = DBWorker.INSTANCE.prepareStatement(query);
            //preparedStatement.setInt(1,id);
            set = preparedStatement.executeQuery(); // В save - аналог
            while (set.next()) {
                travels = new Travels(
                        set.getInt("id"),
                        set.getInt("type"),
                        set.getString("name"),
                        set.getInt("city"),
                        set.getString("image"),
                        set.getString("coords"),
                        set.getInt("price"),
                        set.getInt("supplier")
                );
                listTravels.add(travels);
                //System.out.println(travels);
            }

        } catch (SQLException e) {
            logWarning("Произошёл таймаут бездействия в базе данных.");
            //throw new RuntimeException(e);
        } return listTravels;
    }

    public LinkedList<TravelsRepr> getRepr(TravelsRepr travels) {
        String query = "SELECT travels.id, travels.price, travels.name, cities.name as city, countries.name as country, travels.image, travelTypes.name as travelType from travels\n" +
                "inner join cities on travels.city = cities.id\n" +
                "inner join countries on cities.country = countries.id\n" +
                "inner join travelTypes on travels.type = travelTypes.id";
        StringBuilder sb = new StringBuilder();
        LinkedList<String> parameters = new LinkedList<>();
        LinkedList <TravelsRepr> listTravels = new LinkedList<>();
        if (travels != null)
        {
            if (travels.getId() > 0)
                parameters.add(" id = " + travels.getId());
            if (travels.getType() != null && !travels.getType().isEmpty())
                parameters.add(" travelTypes.name = \"" + travels.getType() + "\"");
            if (travels.getName() != null && !travels.getName().isEmpty())
                parameters.add(" name = \"" + travels.getName() + "\"");
            if (travels.getCity() != null && !travels.getCity().isEmpty())
                parameters.add(" cities.name = \"" + travels.getCity() + "\"");
            if (travels.getCountry() != null && !travels.getCountry().isEmpty())
                parameters.add(" countries.name = \"" + travels.getCountry() + "\"");
            if (travels.getImage() != null && !travels.getImage().isEmpty())
                parameters.add(" image = \"" + travels.getImage() + "\"");
            if (travels.getPrice() > 0)
                parameters.add(" price = " + travels.getPrice());

            if (parameters.size() > 0) {
                sb.append(" where");
                for (String str: parameters)
                    sb.append(str).append(" and");
                sb.delete(sb.length()-4,sb.length());
                query += sb.toString();
            }
            //System.out.println(query);
        }
        ResultSet set;
        try {
            PreparedStatement preparedStatement = DBWorker.INSTANCE.prepareStatement(query);
            //preparedStatement.setInt(1,id);
            set = preparedStatement.executeQuery(); // В save - аналог
            while (set.next()) {
                travels = new TravelsRepr(
                        set.getInt("id"),
                        set.getString("name"),
                        set.getString("city"),
                        set.getString("country"),
                        set.getString("image"),
                        set.getInt("price"),
                        set.getString("travelType")
                        );
                listTravels.add(travels);
                //System.out.println(travels);
            }

        } catch (SQLException e) {
            logWarning("Произошёл таймаут бездействия в базе данных.");
            //throw new RuntimeException(e);
        } return listTravels;
    }

    @Override
    public List<Travels> getAll() {
        List<Travels> travelList = new LinkedList<>();
        try {
            Statement statement = DBWorker.INSTANCE.createStatement();
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
                        set.getInt("supplier")
                );
                //System.out.println(travels);
                travelList.add(travels);
            }
        } catch (SQLException e) {
            logWarning("Произошёл таймаут бездействия в базе данных.");
            //throw new RuntimeException(e);
        } return travelList;
    }

    @Override
    public boolean save(Travels travelsModel) { // insert
        try {
            String query = "insert into travels values (?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.prepareStatement(query);
            preparedStatement.setInt(1,travelsModel.getId());
            preparedStatement.setInt(2,travelsModel.getType());
            preparedStatement.setString(3,travelsModel.getName());
            preparedStatement.setInt(4,travelsModel.getCity());
            preparedStatement.setString(5,travelsModel.getImage());
            preparedStatement.setString(6,travelsModel.getCoordinates());
            preparedStatement.setInt(7,travelsModel.getPrice());
            preparedStatement.setInt(8,travelsModel.getSupplier());
            preparedStatement.execute();
        } catch (SQLException e) {
            logWarning("Произошёл таймаут бездействия в базе данных.");
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Travels travelsModel) {
        try {
            String query = "update travels set type = ?, name = ?, city = ?, image = ?, coords = ?, price = ?, supplier = ? where id = ?";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.prepareStatement(query);
            preparedStatement.setInt(1,travelsModel.getType()); // Будет меняться на одно и то же, поэтому надо замену придумать
            preparedStatement.setString(2,travelsModel.getName());
            preparedStatement.setInt(3,travelsModel.getCity());
            preparedStatement.setString(4,travelsModel.getImage());
            preparedStatement.setString(5,travelsModel.getCoordinates());
            preparedStatement.setInt(6,travelsModel.getPrice());
            preparedStatement.setInt(7,travelsModel.getSupplier());
            preparedStatement.setInt(8,travelsModel.getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            logWarning("Произошёл таймаут бездействия в базе данных.");
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(int id) {
        try {
            String query = "delete from travels where id = ?";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.prepareStatement(query);
            preparedStatement.setInt(1,id); // Будет меняться на одно и то же, поэтому надо замену придумать
            preparedStatement.execute();

        } catch (SQLException e) {
            logWarning("Произошёл таймаут бездействия в базе данных.");
            return false;
        }
        return true;

        //travelsModels.remove(travels); Снос списка
    }


}
