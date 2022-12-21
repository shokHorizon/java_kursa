package Entity;


import java.util.Objects;

public class Tickets {

    public Tickets(){};

    public Tickets(int id, String name, int trip){
        this.id = id;
        this.name = name;
        this.trip = trip;
    }

    public Tickets(String name, int trip){
        this.name = name;
        this.trip = trip;
    }
    private int id;
    private String name;
    private int trip;


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

    public int getTrip() {
        return trip;
    }

    public void setTrip(int trip) {
        this.trip = trip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tickets tickets = (Tickets) o;
        return id == tickets.id && trip == tickets.trip && Objects.equals(name, tickets.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, trip);
    }
}
