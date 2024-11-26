package me.dodas.financeplanner.managers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.dodas.financeplanner.interfaces.Command;
import me.dodas.financeplanner.interfaces.SubCommand;

public class CommandManager {
    static private CommandManager instance = new CommandManager();
    List<Command> commands = new ArrayList<>();
    
    private CommandManager() {};

    public static CommandManager getInstance() {
        return instance;
    }

    public void commandListener(String commandInput) {
        List<String> args = new ArrayList<>(Arrays.asList(commandInput.toLowerCase().split(" ")));
        for(Command cmd : commands) {
            // Verifica se o nome ou algum alias corresponde ao primeiro elemento de args
            if (cmd.getName().equals(args.get(0)) || Arrays.asList(cmd.getAliases()).stream().anyMatch(alias -> args.get(0).equals(alias))) {
                args.remove(0);
                cmd.executeCommand(args);
                subCommandListener(cmd, args);
                return;
            }
        }
        System.out.println("Command not found.");
    }

    public List<Command> getCommandList() {
        return commands;
    }

    public void commandRegister(Command command) {
        commands.add(command);
    }

    // Verify and execute subcommands
    private void subCommandListener(Command cmd, List<String> args) {
        
        if (cmd.getSubcommands().length > 0) {     
            for(SubCommand scmd : cmd.getSubcommands()) {
                if(args.size() > 0) {
                    if (scmd.getName().equals(args.get(0))) {
                        args.remove(0);
                        scmd.executeCommand(args);
                        return;
                    }
                }
            }
            System.out.println("Sub command not found.");
        }

    }
}
