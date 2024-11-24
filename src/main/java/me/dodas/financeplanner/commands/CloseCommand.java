package me.dodas.financeplanner.commands;

import java.util.List;

import me.dodas.financeplanner.Main;
import me.dodas.financeplanner.interfaces.Command;

public class CloseCommand implements Command {

    private String name = "close";
    private String description = "Close the program";
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

    public void executeCommand(List<String> args) {
        Main.stopRunning();
    }
    
}
