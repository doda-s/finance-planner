package me.dodas.financeplanner.commands;

import java.util.Arrays;
import java.util.List;

import me.dodas.financeplanner.entities.CommandManager;
import me.dodas.financeplanner.interfaces.Command;
import me.dodas.financeplanner.interfaces.SubCommand;

public class HelpCommand implements Command{
    private List <Command> commands = CommandManager.getInstance().getCommandList();
    private String name = "help";
    private String description = "help: shows the usage and explains how to use commands.";
    private String[] aliases = {"-h"};

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
        if (args.isEmpty()) {
            printAllCommands(); // Exibe todos os comandos se nenhum argumento for fornecido
            return;
        }
        
        for (String argName : args) { // Itera sobre cada argumento fornecido
            boolean found = false;   // Flag para verificar se o comando foi encontrado
            for (Command cmd : commands) {
                if (cmd.getName().equals(argName) || 
                    Arrays.stream(cmd.getAliases()).anyMatch(alias -> alias.equals(argName))) {
                    printCommand(cmd); // Exibe informações do comando correspondente
                    found = true;
                    break; // Comando encontrado, não precisa continuar a busca
                }
            }
            if (!found) {
                System.out.printf("Comando '%s' não encontrado.%n", argName); // Mensagem de erro para comandos não encontrados
            }
        }
    }
        

    private void printAllCommands(){
        for (Command arg : commands) {
            System.out.println(arg.getName()+": "+arg.getDescription() + " Aliases: " + arg.getAliases());
        }
    }

    private void printCommand(Command arg){
        System.out.println(arg.getName() + ": " + arg.getDescription() + " Aliases: " + arg.getAliases());
    }

    public SubCommand[] getSubcommands() {
        return new SubCommand[0];
    }
}
