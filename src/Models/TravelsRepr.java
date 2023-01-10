package Models;

import java.util.HashMap;

public class TravelsRepr extends Model {
    public TravelsRepr(int id, String name, String city, String image,  int price, String type) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.image = image;
        this.price = price;
        this.type = type;
    }

    private String type;
    private int id;
    private String name;
    private String city;
    private String image;
    private int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }




    @Override
    public String toString() {
        return getClass().getSimpleName() + "{id: " + id
                + ", name: " + name
                + ", city: " + city
                + ", image: " + image
                + ", price: " + price
                + "}";
    }

}
