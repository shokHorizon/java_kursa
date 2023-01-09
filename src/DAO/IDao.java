package DAO;

import Models.Model;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public interface IDao<T> {
    LinkedList<T> get(T model);
    List<T> getAll();
    boolean save(T t);
    boolean update(T t);
    boolean delete(int id);
}
