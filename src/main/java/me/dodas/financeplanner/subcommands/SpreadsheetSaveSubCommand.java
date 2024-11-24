package me.dodas.financeplanner.subcommands;

import java.util.List;

import me.dodas.financeplanner.interfaces.SubCommand;
import me.dodas.financeplanner.managers.SpreadsheetManager;

public class SpreadsheetSaveSubCommand implements SubCommand {

    private String name = "save";
    private String description = "Save the spreadsheet content.";
    
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void executeCommand(List<String> args) {
        try {
            
            SpreadsheetManager.getInstance().saveSpreadsheet();

        } catch (IllegalStateException ise) {

            System.out.println(ise.getMessage());
            
        }
    }
    
}
