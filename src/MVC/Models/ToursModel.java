package MVC.Models;

import MVC.DAO.ToursDao;
import MVC.IDao;
import MVC.Model;

public class ToursModel extends Model {
    int id;
    String city;

    public ToursModel(int id,  String city){
        this.id = id;
        this.city = city;
    }

    public int getId() {
        return id;
    }


    public String getCity() {
        return city;
    }
}
