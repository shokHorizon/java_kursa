package DAO;

import Models.BookRepr;
import Models.Books;
import Models.Users;
import Server.DBWorker;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static Client.SocketClient.log;

public class BooksDao implements IDao<Books> {

    public static final BooksDao INSTANCE = new BooksDao();
    @Override
    public LinkedList<Books> get(Books books) {
        String query = "select * from books";
        StringBuilder sb = new StringBuilder();
        LinkedList<String> parameters = new LinkedList<>();
        LinkedList <Books> listBook = new LinkedList<>();
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
                sb.delete(sb.length()-4,sb.length());
                query += sb.toString();
            }
        }
        ResultSet set;
        //System.out.println(query);
        try {

            PreparedStatement preparedStatement = DBWorker.INSTANCE.prepareStatement(query);
            //preparedStatement.setInt(1,id);
            set = preparedStatement.executeQuery(); // В save - аналог
            while (set.next()) {
                books = new Books(
                        set.getInt("id"),
                        set.getInt("travel"),
                        set.getInt("user"),
                        set.getInt("status")
                );
                listBook.add(books);
                //System.out.println(books);
            }
        } catch (SQLException e) {
            log.warning("Произошёл таймаут бездействия в базе данных.");
            //throw new RuntimeException(e);
        } return listBook;
    }

    public LinkedList<Books> getBySupplier(Books books, int id) {
        String query = "SELECT books.* from books \n" +
                "INNER JOIN travels on books.travel = travels.id\n" +
                "where travels.supplier = ?";
        StringBuilder sb = new StringBuilder();
        LinkedList<String> parameters = new LinkedList<>();
        LinkedList <Books> listBook = new LinkedList<>();
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
        ResultSet set;
        try {
            PreparedStatement preparedStatement = DBWorker.INSTANCE.prepareStatement(query);
            preparedStatement.setInt(1,id);
            set = preparedStatement.executeQuery(); // В save - аналог
            while (set.next()) {
                books = new Books(
                        set.getInt("id"),
                        set.getInt("travel"),
                        set.getInt("user"),
                        set.getInt("status")
                );
                listBook.add(books);
                //System.out.println(books);
            }
        } catch (SQLException e) {
            log.warning("Произошёл таймаут бездействия в базе данных.");
            //throw new RuntimeException(e);
        } return listBook;
    }

    public LinkedList<BookRepr> getByUser(int id) {
        String query = "SELECT books.id, books.status, travels.name from books \n" +
                "INNER JOIN travels on books.travel = travels.id\n" +
                "where books.user = ?";
        StringBuilder sb = new StringBuilder();
        LinkedList<String> parameters = new LinkedList<>();
        LinkedList <BookRepr> listBook = new LinkedList<>();
        ResultSet set;
        try {
            PreparedStatement preparedStatement = DBWorker.INSTANCE.prepareStatement(query);
            preparedStatement.setInt(1,id);
            set = preparedStatement.executeQuery(); // В save - аналог
            while (set.next()) {
                listBook.add(
                        new BookRepr(
                                set.getInt("id"),
                                set.getString("name"),
                                set.getInt("status")
                        )
                );
            }
        } catch (SQLException e) {
            log.warning("Произошёл таймаут бездействия в базе данных.");
            //throw new RuntimeException(e);
        } return listBook;
    }

    @Override
    public List<Books> getAll() {
        List<Books> usersList = new LinkedList<>();
        try {
            Statement statement = DBWorker.INSTANCE.createStatement();
            String query = "select * from books";
            ResultSet set = statement.executeQuery(query);
            while (set.next()){
                Books books = new Books(
                        set.getInt("id"),
                        set.getInt("travel"),
                        set.getInt("user"),
                        set.getInt("status")
                );
                //System.out.println(books);
                usersList.add(books);
            }
        } catch (SQLException e) {
            log.warning("Произошёл таймаут бездействия в базе данных.");
            //throw new RuntimeException(e);
        } return usersList;
    }

    @Override
    public boolean save(Books booksModel) { // insert
        try {
            String query = "insert into books values (?,?,?,?)";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.prepareStatement(query);
            preparedStatement.setInt(1,booksModel.getId());
            preparedStatement.setInt(2,booksModel.getTravel());
            preparedStatement.setInt(3,booksModel.getUser());
            preparedStatement.setInt(4,booksModel.getStatus());
            preparedStatement.execute();
        } catch (SQLException e) {
            log.warning("Произошёл таймаут бездействия в базе данных.");
           return false;
        }
        return true;
    }

    @Override
    public boolean update(Books booksModel) {
        try {
            String query = "update books set travel = ?, user = ?, status = ?  where id = ?";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.prepareStatement(query);
            preparedStatement.setInt(1,booksModel.getTravel()); // Будет меняться на одно и то же, поэтому надо замену придумать
            preparedStatement.setInt(2,booksModel.getUser());
            preparedStatement.setInt(3,booksModel.getStatus());
            preparedStatement.setInt(4,booksModel.getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            log.warning("Произошёл таймаут бездействия в базе данных.");
           return false;
        }
        return true;
    }

    @Override
    public boolean delete(int id) {
        try {
            String query = "delete from books where id = ?";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.prepareStatement(query);
            preparedStatement.setInt(1,id); // Будет меняться на одно и то же, поэтому надо замену придумать
            preparedStatement.execute();

        } catch (SQLException e) {
            log.warning("Произошёл таймаут бездействия в базе данных.");
           return false;
        }
        return true;

        //booksModels.remove(books); Снос списка
    }


}
