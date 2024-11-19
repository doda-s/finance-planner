package me.dodas.financeplanner.exceptions;

public class AlreadyOpenedSpreadsheet extends Exception {
    public AlreadyOpenedSpreadsheet() {
        super("Already have an open spreadsheet.");	
    }
    
    public AlreadyOpenedSpreadsheet(String message) {
        super(message);	
    }
    
    public AlreadyOpenedSpreadsheet(String message, Throwable cause) {
        super(message, cause);	
    }
}
