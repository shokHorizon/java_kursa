package Models;

public class BookRepr extends Model {
    private int id;
    private int name;
    private int status;

    public BookRepr(int id, int name, int status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
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
                + ", travel: " + name
                + ", status: " + status
                + "}";
    }
}
