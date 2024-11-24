package me.dodas.financeplanner.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Spreadsheet {
    private String name;
    private String creationDate;
    private String lastUpdateDate;

    private List <MonthlyRegister> monthlyRegister = new ArrayList<>();

    public void addMonthlyRegister(LocalDate date){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMyyyy");
        int id = Integer.parseInt(dtf.format(date));

        MonthlyRegister register = new MonthlyRegister(id);
        monthlyRegister.add(register);

        dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        lastUpdateDate = LocalDate.now().format(dtf);
    }

    public MonthlyRegister getMonthlyRegister(int id){
        for (MonthlyRegister register : monthlyRegister) {
            if(register.getId() == id){
                return register;
            }
        }
        return null;
    }


    public String getCreationDate() {
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
        LocalDate date = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.name = name;
        this.creationDate = date.format(dtf);
        this.lastUpdateDate = creationDate;
    }

    
}
