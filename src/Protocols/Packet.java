package Protocols;

import MVC.IModel;

import java.io.Serializable;
import java.util.Map;
import java.util.stream.Stream;

public class Packet {
    int code;
    IModel models;

    public static final Map<Integer, String> CODES = Map.ofEntries(
            Map.entry(1, "Tours"),
            Map.entry(2, "Parser")
    );

    public Packet(Stream stream){

    }

    public Packet(int code, IModel models){
        this.code = code;
        this.models = models;
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

    public String getCodeName(int code){
        return this.CODES.get(code);
    }
}
