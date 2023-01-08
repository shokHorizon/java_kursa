package DAO;

import Models.Model;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public interface IDao<T> {
    LinkedList<T> get(T model);
    List<T> getAll();
    void save(T t);
    void update(T t);
    void delete(int id);
}
