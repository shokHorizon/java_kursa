package Server;

import MVC.IModel;

import java.util.stream.Stream;

public class Packet {
    int code;
    int object_count;
    IModel models;

    public Packet(Stream stream){

    }

    public void write_to_stream(Stream stream){
        return;
    }
}
