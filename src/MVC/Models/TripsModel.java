package MVC.Models;
import MVC.Model;
import java.sql.Timestamp;

public class TripsModel extends Model {

    int id;
    int price;
    Timestamp date;

    public TripsModel(int id, int price, Timestamp date) {
        this.id = id;
        this.price = price;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public Timestamp getDate() {
        return date;
    }
}
