package me.dodas.financeplanner.models;

public class Expense extends Registry {

    private String id;
    private String name;
    private String description;
    private double value;
    private String creationDate;
        
    public Expense (String id) {
        this.id = id;
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

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

}