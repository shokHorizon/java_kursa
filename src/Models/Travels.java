package Models;

public class Travels extends Model {
    public Travels(int id, int type, String name, int city, String image, String coordinates, int price, String supplier) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.city = city;
        this.image = image;
        this.coordinates = coordinates;
        this.price = price;
        this.supplier = supplier;
    }

    private int id;
    private int type;
    private String name;
    private int city;
    private String image;
    private String coordinates;
    private int price;
    private String supplier;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
}
