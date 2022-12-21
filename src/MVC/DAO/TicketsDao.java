package MVC.DAO;

import Entity.Tickets;
import Entity.Tours;
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
        return Optional.empty();
    }

    @Override
    public List getAll() {
        try {
            Statement statement = DBWorker.INSTANCE.getConnection().createStatement();
            String query = "select * from tickets";
            ResultSet set = statement.executeQuery(query);
            while (set.next()){
                Tickets tick = new Tickets();
                tick.setId(set.getInt("id"));
                tick.setName(set.getString("name"));
                tick.setTrip(set.getInt("trip"));
                System.out.println(tick);}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public void save(TicketsModel ticketsModel) {
//        try {
//            Statement statement = DBWorker.INSTANCE.getConnection().createStatement();
//            String query = "insert into tickets values (?,?,?)";
//            ResultSet set = statement.executeQuery(query);
//            while (set.next()){
//                Tickets tick = new Tickets();
//                ticketsModel.setId(set.getInt("id"));
//                ticketsModel.setName(set.getString("name"));
//                ticketsModel.setTrip(set.getInt("trip"));
//                System.out.println(tick);}
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

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
    public void delete(int id) {

    }
}
