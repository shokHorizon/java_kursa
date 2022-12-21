package MVC.DAO;

import Entity.Tickets;
import Entity.Tours;
import MVC.IDao;
import MVC.Models.ToursModel;
import Server.DBWorker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ToursDao implements IDao<ToursModel> {
    private List<ToursModel> toursModels = new ArrayList<>();

    public ToursDao(){};
    public ToursDao(ToursModel toursModel){
        toursModels.add(toursModel);
    }
    public ToursDao(List<ToursModel> toursModels){
        this.toursModels.addAll(toursModels);
    }
    @Override
    public Optional<ToursModel> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<ToursModel> getAll() {

        try {
            Statement statement = DBWorker.INSTANCE.getConnection().createStatement();
            String query = "select * from tours";
            ResultSet set = statement.executeQuery(query);
            while (set.next()){
                Tours tours = new Tours();
                tours.setId(set.getInt("id"));
                tours.setCity(set.getString("city"));
                System.out.println(tours);}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } return null;
    }

    @Override
    public void save(ToursModel toursModel) {
        return;
    }

    @Override
    public void update(ToursModel toursModel, String[] params) {
        return;
    }

    @Override
    public void delete(ToursModel toursModel) {
        toursModels.remove(toursModel);
    }
}
