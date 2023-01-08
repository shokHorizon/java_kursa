package DAO;

import Models.Books;
import Server.DBWorker;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class BooksDao implements IDao<Books> {

    public static final BooksDao INSTANCE = new BooksDao();
    @Override
    public Optional<Books> get(Books books) {
        String query = "select * from books";
        StringBuilder sb = new StringBuilder();
        LinkedList<String> parameters = new LinkedList<>();
        if (books != null)
        {
            if (books.getId() > 0)
                parameters.add(" id = " + books.getId());
            if (books.getTravel() > 0)
                parameters.add(" travel = " + books.getTravel());
            if (books.getUser() > 0)
                parameters.add(" user = " + books.getUser());
            if (books.getStatus() >= 0)
                parameters.add(" status = " + books.getStatus());
            if (parameters.size() > 0) {
                sb.append(" where");
                for (String str: parameters)
                    sb.append(str).append(" and");
                sb.delete(sb.length()-5,sb.length()-1);
                query += sb.toString();
            }
        }
        try {

            PreparedStatement preparedStatement = DBWorker.INSTANCE.getConnection().prepareStatement(query);
            //preparedStatement.setInt(1,id);
            ResultSet set = preparedStatement.executeQuery(); // В save - аналог
            books = new Books(
                    set.getInt("id"),
                    set.getInt("travel"),
                    set.getInt("user"),
                    set.getInt("status")
            );
            System.out.println(books);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } return Optional.of(books);
    }

    @Override
    public List<Books> getAll() {

        try {
            Statement statement = DBWorker.INSTANCE.getConnection().createStatement();
            String query = "select * from books";
            ResultSet set = statement.executeQuery(query);
            while (set.next()){
                Books books = new Books(
                        set.getInt("id"),
                        set.getInt("travel"),
                        set.getInt("user"),
                        set.getInt("status")
                );
                System.out.println(books);}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } return null;
    }

    @Override
    public void save(Books booksModel) { // insert
        try {
            String query = "insert into books values (?,?,?,?)";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,booksModel.getId());
            preparedStatement.setInt(2,booksModel.getTravel());
            preparedStatement.setInt(3,booksModel.getUser());
            preparedStatement.setInt(4,booksModel.getStatus());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return;
    }

    @Override
    public void update(Books booksModel) {
        try {
            String query = "update books set travel = ?, user = ?, status = ?  where id = ?";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,booksModel.getTravel()); // Будет меняться на одно и то же, поэтому надо замену придумать
            preparedStatement.setInt(2,booksModel.getUser());
            preparedStatement.setInt(3,booksModel.getStatus());
            preparedStatement.setInt(4,booksModel.getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return;
    }

    @Override
    public void delete(int id) {
        try {
            String query = "delete from books where id = ?";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,id); // Будет меняться на одно и то же, поэтому надо замену придумать
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return;

        //booksModels.remove(books); Снос списка
    }
}
