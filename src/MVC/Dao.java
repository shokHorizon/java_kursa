package MVC;

import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public abstract class Dao<T> {

    //private static final String querySelect = "Select * from ?";

    abstract Optional<T> getStatement(long id);

    abstract List<T> getAllStatement();

    abstract void saveStatement(T t);

    abstract void updateStatement(T t, String[] params);

    abstract void deleteStatement(T t);
}