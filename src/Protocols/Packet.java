package Protocols;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Stream;

public class Packet <T> implements Serializable {
    List<T> models;
    QueryModel queryModel;
    QueryMethod queryMethod;

    public Packet() {

    }

    public Packet(QueryModel queryModel, T model){
        List<T> temp = new ArrayList<>();
        temp.add(model);
        this.queryModel = queryModel;
        this.models = temp;
    }

    public Packet(QueryModel queryModel, QueryMethod queryMethod, T model){
        List<T> temp = new ArrayList<>();
        temp.add(model);
        this.queryModel = queryModel;
        this.models = temp;
        this.queryMethod = queryMethod;
    }

    public Packet(QueryModel queryModel, QueryMethod queryMethod, List<T> models){
        this.queryModel = queryModel;
        this.models = models;
        this.queryMethod = queryMethod;
    }

    public void Print(){
        System.out.println(this.queryModel);
        System.out.println(this.models);

    }

    public Packet(Stream stream){

    }

    public void write_to_stream(Stream stream){
        return;
    }
    public QueryModel getQueryModel() {
        return queryModel;
    }
    public void setQueryModel(QueryModel queryModel) {
        this.queryModel = queryModel;
    }
    public QueryMethod getQueryMethod() {return queryMethod;}

    public void setQueryMethod(QueryMethod queryMethod) {this.queryMethod = queryMethod;}

    public List<T> getModels(){
        return models;
    }
}
