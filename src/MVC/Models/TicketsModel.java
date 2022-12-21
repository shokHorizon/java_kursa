package MVC.Models;

import MVC.Model;

public class TicketsModel extends Model {
    int id;
    String name;
    int trip;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTrip() {
        return trip;
    }

    public TicketsModel(int id, String name, int trip) {
        this.id = id;
        this.name = name;
        this.trip = trip;
    }
}
