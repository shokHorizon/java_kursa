package Entity;

import java.util.Objects;


public class Tours {
    private int id;
    private String city;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tours tours = (Tours) o;
        return id == tours.id && Objects.equals(city, tours.city);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, city);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{id: " + id
                + ", username: " + city
                + ")";
    }
}
