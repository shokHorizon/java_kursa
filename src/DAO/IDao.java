package DAO;

import Models.Model;

import java.util.List;
import java.util.Optional;

public interface IDao<T> {
    Optional<T> get(T model);
    List<T> getAll();
    void save(T t);
    void update(T t);
    void delete(int id);
}
