package Models;

public class Books extends Model {
    private int id;
    private int user;
    private int travel;

    private int status;

    public Books(int id, int travel, int user, int status) {
        this.id = id;
        this.user = user;
        this.travel = travel;
        this.status = status;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{id: " + id
                + ", travel: " + travel
                + ", user: " + user
                + ", status: " + status
                + "}";
    }
}
