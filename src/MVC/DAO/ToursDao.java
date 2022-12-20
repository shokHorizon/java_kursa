package MVC.DAO;

import MVC.IDao;
import MVC.Models.ToursModel;

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
        return null;
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
