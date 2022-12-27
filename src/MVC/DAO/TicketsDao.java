package MVC.DAO;


import MVC.IDao;
import MVC.Models.TicketsModel;
import Server.DBWorker;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class TicketsDao implements IDao<TicketsModel> {

    public static final TicketsDao INSTANCE = new TicketsDao();

    String Tname = "tickets";
    @Override
    public Optional<TicketsModel> get(int id) {
        try {
            String query = "select * from tickets where id = ?";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet set = preparedStatement.executeQuery(query); // В save - аналог
            TicketsModel tickets = new TicketsModel(
                set.getInt("id"),
                set.getString("name"),
                set.getInt("trip"));
            System.out.println(tickets);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } return null;
    }

    @Override
    public List getAll() {
        try {
            Statement statement = DBWorker.INSTANCE.getConnection().createStatement();
            String query = "select * from tickets";
            ResultSet set = statement.executeQuery(query);
            while (set.next()){
                TicketsModel tick = new TicketsModel(
                set.getInt("id"),
                set.getString("name"),
                set.getInt("trip"));
                System.out.println(tick.toString());}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public void save(TicketsModel ticketsModel) {
        try {
            String query = "insert into tickets values (?,?,?)";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.getConnection().prepareStatement(query);

                preparedStatement.setInt(1,ticketsModel.getId());
                preparedStatement.setString(2,ticketsModel.getName());
                preparedStatement.setInt(3,ticketsModel.getTrip());
                preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(TicketsModel ticketsModel) {
        try {
            String query = "update tickets set name = ?, trip = ? where id = ?";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.getConnection().prepareStatement(query);
            preparedStatement.setString(1,ticketsModel.getName()); // Будет меняться на одно и то же, поэтому надо замену придумать
            preparedStatement.setInt(2,ticketsModel.getTrip());
            preparedStatement.setInt(3,ticketsModel.getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return;
    }

    @Override
    public void delete(int id){
    try{
        String query = "delete from tickets where id = ?";
        PreparedStatement preparedStatement = DBWorker.INSTANCE.getConnection().prepareStatement(query);
        preparedStatement.setInt(1,id); // Будет меняться на одно и то же, поэтому надо замену придумать
        preparedStatement.execute();

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
        return;
    }
}
