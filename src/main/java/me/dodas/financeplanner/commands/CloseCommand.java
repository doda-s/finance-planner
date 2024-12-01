package me.dodas.financeplanner.commands;

import java.util.List;

import me.dodas.financeplanner.Main;
import me.dodas.financeplanner.interfaces.Command;
import me.dodas.financeplanner.interfaces.SubCommand;
import me.dodas.financeplanner.managers.SpreadsheetManager;

public class CloseCommand implements Command {

    private String name = "close";
    private String description = "Close the program.";
    private String[] aliases = new String[]{};

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String[] getAliases() {
        return aliases;
    }

    public SubCommand[] getSubcommands() {
        return new SubCommand[0];
    }

    public void executeCommand(List<String> args) {
        try {
            SpreadsheetManager.getInstance().saveSpreadsheet();
        } catch (IllegalStateException ise) {
            System.out.println(ise.getMessage());
        }
        Main.stopRunning();
    }
    
    
}
