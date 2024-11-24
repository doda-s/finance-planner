package me.dodas.financeplanner.commands;

import java.util.ArrayList;
import java.util.List;

import me.dodas.financeplanner.interfaces.Command;
import me.dodas.financeplanner.interfaces.SubCommand;
import me.dodas.financeplanner.subcommands.SpreadsheetCreateSubCommand;

public class SpreadsheetCommand implements Command {

    private String name = "spreadsheet";
    private String description = "Used to manipulate spreadsheets.";
    private String[] aliases = new String[]{"sheet"};
    private List<SubCommand> subCommands = new ArrayList<>();

    public SpreadsheetCommand() {
        loadSubCommands();
    }

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
        return subCommands.toArray(new SubCommand[0]);
    }

    public void executeCommand(List<String> args) {
        return;
    }

    private void loadSubCommands() {
        subCommands.add(new SpreadsheetCreateSubCommand());
    }

    
}
