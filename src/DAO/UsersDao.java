package DAO;

import Models.Travels;
import Models.Users;
import Server.DBWorker;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class UsersDao implements IDao<Users>{

    public static final UsersDao INSTANCE = new UsersDao();
    @Override
    public Optional<Users> get(int id) {

        try {
            String query = "select * from users where id = ?";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet set = preparedStatement.executeQuery(); // В save - аналог
            Users users = new Users(
                    set.getInt("id"),
                    set.getString("login"),
                    set.getInt("hashpassword"),
                    set.getInt("accessLevel")
            );
            System.out.println(users);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } return null;
    }

    @Override
    public List<Users> getAll() {

        try {
            Statement statement = DBWorker.INSTANCE.getConnection().createStatement();
            String query = "select * from users";
            ResultSet set = statement.executeQuery(query);
            while (set.next()){
                Users users = new Users(
                        set.getInt("id"),
                        set.getString("login"),
                        set.getInt("hashpassword"),
                        set.getInt("accessLevel")
                );
                System.out.println(users);}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } return null;
    }

    @Override
    public void save(Users usersModel) { // insert
        try {
            String query = "insert into users values (?,?,?,?)";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,usersModel.getId());
            preparedStatement.setString(2,usersModel.getLogin());
            preparedStatement.setInt(3,usersModel.getHashedPassword());
            preparedStatement.setInt(4,usersModel.getAccessLevel());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return;
    }

    @Override
    public void update(Users usersModel) {
        try {
            String query = "update users set login = ?, hashpassword = ?, accessLevel = ?  where id = ?";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.getConnection().prepareStatement(query);
            preparedStatement.setString(1,usersModel.getLogin()); // Будет меняться на одно и то же, поэтому надо замену придумать
            preparedStatement.setInt(2,usersModel.getHashedPassword());
            preparedStatement.setInt(3,usersModel.getAccessLevel());
            preparedStatement.setInt(4,usersModel.getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return;
    }

    @Override
    public void delete(int id) {
        try {
            String query = "delete from users where id = ?";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,id); // Будет меняться на одно и то же, поэтому надо замену придумать
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return;

        //usersModels.remove(users); Снос списка
    }
    
}
