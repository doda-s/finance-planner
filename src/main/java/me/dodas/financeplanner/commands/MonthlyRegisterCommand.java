package me.dodas.financeplanner.commands;

import java.util.ArrayList;
import java.util.List;

import me.dodas.financeplanner.interfaces.Command;
import me.dodas.financeplanner.interfaces.SubCommand;

public class MonthlyRegisterCommand implements Command {

	private String name = "monthly-register";
    private String description = "Control the monthly registers in a spreadsheet.";
    private String[] aliases = new String[]{"mr"};
    private List<SubCommand> subCommands = new ArrayList<>();
    
    public MonthlyRegisterCommand() {
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

    // Load the sub commands :D
    private void loadSubCommands() {

    }
    
}
