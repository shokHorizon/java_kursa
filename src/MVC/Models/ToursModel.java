package MVC.Models;

import MVC.DAO.ToursDao;
import MVC.IDao;
import MVC.Model;

public class ToursModel extends Model {
    int id;
    int price;
    String city;

    public ToursModel(int id, int price, String city){
        this.id = id;
        this.price = price;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public String getCity() {
        return city;
    }
}
