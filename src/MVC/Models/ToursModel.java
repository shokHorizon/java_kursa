package MVC.Models;

import MVC.IModel;

public class ToursModel implements IModel {
    int id;
    int price;
    String city;

    public ToursModel(int id, int price, String city){
        this.id = id;
        this.price = price;
        this.city = city;
    }
}
