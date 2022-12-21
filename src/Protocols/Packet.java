package Protocols;

import MVC.Model;
import MVC.Models.ToursModel;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Stream;

public class Packet implements Serializable {
    int code;
    List<Object> models;

    public static final Map<Integer, String> CODES = Map.ofEntries(
            Map.entry(1, "Tours"),
            Map.entry(2, "Parser")
    );

    public Packet() {

    }

    public Packet(Integer integer, List<ToursModel> all) {
        this.code = integer;
        this.models = Collections.singletonList(all);
    }

    public void Print(){
        System.out.println(this.code);
        System.out.println(this.models);

    }

    public Packet(Stream stream){

    }

    public Packet(int code, Model model){
        List<Object> temp = new ArrayList<>();
        temp.add(model);
        this.code = code;
        this.models = temp;
    }

    public Packet(int code, List<Model> models){
        this.code = code;
        this.models = Collections.singletonList(models);
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

    public List<Object> getModels(){
        return models;
    }

    public String getCodeName(int code){
        return this.CODES.get(code);
    }
}
