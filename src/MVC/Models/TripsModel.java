package MVC.Models;
import MVC.Model;
import java.sql.Timestamp;

public class TripsModel extends Model {

    int id;
    int tour;
    int price;
    Timestamp date;

    public TripsModel(int id, int tour, int price, Timestamp date) {
        this.id = id;
        this.tour = tour;
        this.price = price;
        this.date = date;
    }

    public TripsModel() {
    }

    public int getId() {
        return id;
    }

    public int getTour() {
        return tour;
    }

    public int getPrice() {
        return price;
    }

    public Timestamp getDate() {
        return date;
    }


}
