package DAO;

import Models.Travels;
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

// Фильтровать юзеров. То, что null - ск
public class UsersDao implements IDao<Users>{

    public static final UsersDao INSTANCE = new UsersDao();

    @Override
    public LinkedList<Users> get(Users users) {
        String query = "select * from users";
        StringBuilder sb = new StringBuilder();
        LinkedList <String> parameters = new LinkedList<>();
        LinkedList <Users> listUser = new LinkedList<>();
        if (users != null)
        {
            if (users.getId() > 0)
                parameters.add(" id = " + users.getId());
            if (users.getLogin() != null)
                parameters.add(" login = " + users.getLogin());
            if (users.getHashedPassword() != 0)
                parameters.add(" hashpassword = " + users.getHashedPassword());
            if (users.getAccessLevel() >= 0)
                parameters.add(" accessLevel = " + users.getAccessLevel());
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
            //preparedStatement.setInt(1,id);
            set = preparedStatement.executeQuery(); // В save - аналог
            while (set != null) {
                users = new Users(
                        set.getInt("id"),
                        set.getString("login"),
                        set.getInt("hashpassword"),
                        set.getInt("accessLevel")
                );
                //System.out.println(users);
                listUser.add(users);
                set.next();
            }
        } catch (SQLException e) {
            log.warning("Произошёл таймаут бездействия в базе данных.");
            //throw new RuntimeException(e);
        } return listUser;
    }

    @Override
    public List<Users> getAll() {
        List<Users> usersList = new LinkedList<>();
        try {
            Statement statement = DBWorker.INSTANCE.createStatement();
            String query = "select * from users";
            ResultSet set = statement.executeQuery(query);
            while (set.next()){
                Users users = new Users(
                        set.getInt("id"),
                        set.getString("login"),
                        set.getInt("hashpassword"),
                        set.getInt("accessLevel")
                );
                //System.out.println(users);
                usersList.add(users);
            }
        } catch (SQLException e) {
            log.warning("Произошёл таймаут бездействия в базе данных.");
            //throw new RuntimeException(e);
        } return usersList;
    }

    @Override
    public boolean save(Users usersModel) { // insert
        try {
            String query = "insert into users values (?,?,?,?)";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.prepareStatement(query);
            preparedStatement.setInt(1,usersModel.getId());
            preparedStatement.setString(2,usersModel.getLogin());
            preparedStatement.setInt(3,usersModel.getHashedPassword());
            preparedStatement.setInt(4,usersModel.getAccessLevel());
            preparedStatement.execute();
        } catch (SQLException e) {
            log.warning("Произошёл таймаут бездействия в базе данных.");
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Users usersModel) {
        try {
            String query = "update users set login = ?, hashpassword = ?, accessLevel = ?  where id = ?";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.prepareStatement(query);
            preparedStatement.setString(1,usersModel.getLogin()); // Будет меняться на одно и то же, поэтому надо замену придумать
            preparedStatement.setInt(2,usersModel.getHashedPassword());
            preparedStatement.setInt(3,usersModel.getAccessLevel());
            preparedStatement.setInt(4,usersModel.getId());
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
            String query = "delete from users where id = ?";
            PreparedStatement preparedStatement = DBWorker.INSTANCE.prepareStatement(query);
            preparedStatement.setInt(1,id); // Будет меняться на одно и то же, поэтому надо замену придумать
            preparedStatement.execute();

        } catch (SQLException e) {
            log.warning("Произошёл таймаут бездействия в базе данных.");
            return false;
        }
        return true;
        //usersModels.remove(users); Снос списка
    }

    public Optional<Users> getByLogin(String login) {
        Users users;
        while(true) {
            try {
                String query = "select * from users where login = ?";
                PreparedStatement preparedStatement = DBWorker.INSTANCE.prepareStatement(query);
                preparedStatement.setString(1, login);
                ResultSet set = preparedStatement.executeQuery();
                if (!set.next()) {
                    return Optional.empty();
                }
                users = new Users(
                        set.getInt("id"),
                        set.getString("login"),
                        set.getInt("hashpassword"),
                        set.getInt("accessLevel")
                );
                //System.out.println(users);
                break;
            } catch (SQLException e) {
                log.warning("Произошёл таймаут бездействия в базе данных.");
                //throw new RuntimeException(e);
            }
        }
        return Optional.of(users);
    }
}
