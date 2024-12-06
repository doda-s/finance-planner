package me.dodas.financeplanner.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Spreadsheet {
    private String name;
    private String creationDate;
    private String lastUpdateDate;

    private List <MonthlyRegister> monthlyRegisters = new ArrayList<>();

    public void addMonthlyRegister(int month, int year){
        if(monthlyRegisters == null) {
            monthlyRegisters = new ArrayList<>();
        }

        Date nowDate = new Date();
        Calendar calendar = Calendar.getInstance();
        
        calendar.setTime(nowDate);
        
        if (month > 12 || month < 1) {
            month = calendar.get(Calendar.MONTH) + 1; // Meses começam em 0 (Janeiro = 0)
        }

        if (year == 0) {
            year = calendar.get(Calendar.YEAR);
        }

        String monthlyRegisterId = String.format("%s%s", month, year);

        for(MonthlyRegister mr : monthlyRegisters) {
            if(mr.getId() == monthlyRegisterId) {
                throw new IllegalArgumentException(
                    String.format("There is already a monthly register in month '%s' of year '%s'", month, year
                ));
            }
        }

        monthlyRegisters.add(new MonthlyRegister(monthlyRegisterId));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        lastUpdateDate = sdf.format(nowDate);
    }

    public boolean removeMonthlyRegister(MonthlyRegister mr) {
        return monthlyRegisters.remove(mr);
    }

    public MonthlyRegister getMonthlyRegisterById(String id){
        for (MonthlyRegister register : monthlyRegisters) {
            if(register.getId().equals(id)){
                return register;
            }
        }
        return null;
    }

    /* Caso não tenha nenhum monthly register salvo no arquivo Json, ao desserializar a lista monthlyRegister fica null.
     * Ou seja, é necessário fazer uma verificação toda vez que essa função for chamada.
    */
    public List<MonthlyRegister> getMonthlyRegister(){
    	if(monthlyRegisters != null) {
    		return monthlyRegisters;
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
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.name = name;
        this.creationDate = sdf.format(date);
        this.lastUpdateDate = creationDate;
    }

    
}
