package Models;

public class Users extends Model {
    private int id;
    private String login;
    private int hashedPassword;
    private int accessLevel;

    public Users(int id, String login, int hashedPassword, int accessLevel) {
        this.id = id;
        this.login = login;
        this.hashedPassword = hashedPassword;
        this.accessLevel = accessLevel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(int hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }
}
