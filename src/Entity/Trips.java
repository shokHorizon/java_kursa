package Entity;


import java.sql.Timestamp;
import java.util.Objects;


public class Trips {
    private int id;
    private int tour;
    private int price;
    private Timestamp date;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTour() {
        return tour;
    }

    public void setTour(int tour) {
        this.tour = tour;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trips trips = (Trips) o;
        return id == trips.id && tour == trips.tour && price == trips.price && Objects.equals(date, trips.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tour, price, date);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{id: " + id
                + ", tour: " + tour
                + ", price: " + price
                + ", date: " + date.toString()
                + "}";
    }
}
