package me.dodas.financeplanner.models;

public abstract class Registry {
    public abstract String getId();
    public abstract String getName();
    public abstract void setName(String name);
    public abstract String getDescription();
    public abstract void setDescription(String description);
    public abstract double getValue();
    public abstract void setValue(double value);
    public abstract String getCreationDate();
    public abstract void setCreationDate(String creationDate);
}
