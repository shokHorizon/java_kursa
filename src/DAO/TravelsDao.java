package DAO;

import Models.Travels;
import Models.Users;
import Server.DBWorker;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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
            if (travels.getSupplier() != null)
                parameters.add(" supplier = " + travels.getSupplier());

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

            PreparedStatement preparedStatement = DBWorker.INSTANCE.getConnection().prepareStatement(query);
            //preparedStatement.setInt(1,id);
            set = preparedStatement.executeQuery(); // В save - аналог
            travels = new Travels(
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
        } return listTravels;
    }

    @Override
    public List<Travels> getAll() {
        List<Travels> travelList = new LinkedList<>();
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
                        set.getString("supplier")
                );
                System.out.println(travels);
                travelList.add(travels);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } return travelList;
    }

    @Override
    public void save(Travels travelsModel) { // insert
        try {
            String query = "insert into travels values (?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,travelsModel.getId());
            preparedStatement.setInt(2,travelsModel.getType());
            preparedStatement.setString(3,travelsModel.getName());
            preparedStatement.setInt(4,travelsModel.getCity());
            preparedStatement.setString(5,travelsModel.getImage());
            preparedStatement.setString(6,travelsModel.getCoordinates());
            preparedStatement.setInt(7,travelsModel.getPrice());
            preparedStatement.setString(8,travelsModel.getSupplier());
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
