package me.dodas.financeplanner.commands;

import java.util.ArrayList;
import java.util.List;

import me.dodas.financeplanner.interfaces.Command;
import me.dodas.financeplanner.interfaces.SubCommand;

public class RegistryCommand implements Command {

    private String name = "registry";
    private String description = "";
    private String[] aliases = new String[]{"reg"};
    private List<SubCommand> subCommands = new ArrayList<>();

    public RegistryCommand() {
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
        
    }

    // Load sub commands XD
    private void loadSubCommands() {

    }
    
}
