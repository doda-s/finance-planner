package me.dodas.financeplanner.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Spreadsheet {
    private String name;
    private Date creationDate;
    private String lastUpdateDate;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMyyyy");
    private List <MonthlyRegister> monthlyRegister = new ArrayList<>();

    public void addMonthlyRegister(LocalDate date){
        int id = Integer.parseInt(dtf.format(date));
        MonthlyRegister register = new MonthlyRegister(id);
        monthlyRegister.add(register);
    }

    public MonthlyRegister getMonthlyRegister(int id){
        for (MonthlyRegister register : monthlyRegister) {
            if(register.getId() == id){
                return register;
            }
        }
        return null;
    }


    public Date getCreationDate() {
        return creationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Spreadsheet(String name){
        this.name = name;
        this.creationDate = new Date();
    }

    
}
