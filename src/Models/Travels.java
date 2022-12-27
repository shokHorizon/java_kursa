package Models;

public class Travels extends Model {
    private int id;
    private int city;
    private String name;
    private int user;
    private int price;
    private String coordinates;
    private String image;

    public Travels(int id, int city, String name, int user, int price, String coordinates, String image) {
        this.id = id;
        this.city = city;
        this.name = name;
        this.user = user;
        this.price = price;
        this.coordinates = coordinates;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
