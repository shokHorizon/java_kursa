package Protocols;

import MVC.Model;
import MVC.Models.TicketsModel;
import MVC.Models.ToursModel;
import MVC.Models.TripsModel;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Stream;

public class Packet <T> implements Serializable {
    int code;
    List<T> models;

    public static final Map<Integer, String> CODES = Map.ofEntries(
            Map.entry(1, "Tours"),
            Map.entry(2, "Parser")
    );

    public Packet() {

    }

    public Packet(int code, T model){
        List<T> temp = new ArrayList<>();
        temp.add(model);
        this.code = code;
        this.models = temp;
    }

    public Packet(int code, List<T> models){
        this.code = code;
        this.models = models;
    }

    public void Print(){
        System.out.println(this.code);
        System.out.println(this.models);

    }

    public Packet(Stream stream){

    }

    public void write_to_stream(Stream stream){
        return;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<T> getModels(){
        return models;
    }

    public String getCodeName(int code){
        return this.CODES.get(code);
    }
}
