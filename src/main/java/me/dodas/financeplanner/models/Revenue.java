package me.dodas.financeplanner.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Revenue extends Registry {

    private String id;
    private String name;
    private String description;
    private double value;
    private String creationDate;

    public Revenue (String id) {
        this.id = id;
        Date nowDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        creationDate = sdf.format(nowDate);
    }
    
    public String getId() {
        return id;
    }
    
    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getValue(){
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getCreationDate(){
        return creationDate;
    }

    //remover da classe abstrata.
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

}
