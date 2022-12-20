package MVC.DAO;

import MVC.IDao;

import java.util.List;
import java.util.Optional;

public class TripsDao implements IDao {
    @Override
    public Optional get(long id) {
        return Optional.empty();
    }

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public void save(Object o) {

    }

    @Override
    public void update(Object o, String[] params) {

    }

    @Override
    public void delete(Object o) {

    }
}
