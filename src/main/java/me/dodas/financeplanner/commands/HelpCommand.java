package me.dodas.financeplanner.commands;

import java.util.Arrays;
import java.util.List;

import me.dodas.financeplanner.interfaces.Command;
import me.dodas.financeplanner.interfaces.SubCommand;
import me.dodas.financeplanner.managers.CommandManager;

public class HelpCommand implements Command{
    private List <Command> commands = CommandManager.getInstance().getCommandList();
    private String name = "help";
    private String description = "Shows the usage and explains how to use commands.";
    private String[] aliases = {"h"};

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
            printAllCommands();
            System.out.println(""); // Exibe todos os comandos se nenhum argumento for fornecido
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
            String aliaseString = "";

            if(arg.getAliases().length != 0){
                for(int i = 0; i < arg.getAliases().length; i++){
                    aliaseString.replace("none", "");
                    if(arg.getAliases().length - 1 == i){
                        aliaseString += String.format("%s.", arg.getAliases()[i]);
                        break;
                    }
                    System.out.println(arg.getAliases()[i] + ", ");
                    aliaseString += String.format("%s, ", arg.getAliases()[i]);
                }
                System.out.println(arg.getName() +": "+ arg.getDescription() + " Aliases:" + aliaseString);        
            }else{
                System.out.println(arg.getName() +": "+ arg.getDescription());
            }
        }
    }

    private void printCommand(Command arg){
        System.out.printf("%s: %s   /Aliases: ",arg.getName(),arg.getDescription());
        for(int i = 0; i < arg.getAliases().length; i++){
            if(arg.getAliases().length - 1 == i){
                System.out.println(arg.getAliases()[i] + ".");
                return;
            }
            System.out.println(arg.getAliases()[i] + ", ");
        }
    }

    public SubCommand[] getSubcommands() {
        return new SubCommand[0];
    }
}
