package Models;

public class Books extends Model {
    private int id;
    private int user;
    private int travel;

    public Books(int id, int user, int travel) {
        this.id = id;
        this.user = user;
        this.travel = travel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getTravel() {
        return travel;
    }

    public void setTravel(int travel) {
        this.travel = travel;
    }
}
