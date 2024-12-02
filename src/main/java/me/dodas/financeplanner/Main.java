package me.dodas.financeplanner;

import java.util.Scanner;

import me.dodas.financeplanner.commands.CloseCommand;
import me.dodas.financeplanner.commands.HelpCommand;
import me.dodas.financeplanner.commands.MonthlyRegisterCommand;
import me.dodas.financeplanner.commands.RegistryCommand;
import me.dodas.financeplanner.commands.SpreadsheetCommand;
import me.dodas.financeplanner.managers.CommandManager;
import me.dodas.financeplanner.managers.ConfigManager;
import me.dodas.financeplanner.managers.DirectoryManager;

public class Main {
    private static ConfigManager configManager = ConfigManager.getInstance();
    private static DirectoryManager directoryManager = DirectoryManager.getInstance();
    private static CommandManager commandManager = CommandManager.getInstance();
    private static Scanner scanner = new Scanner(System.in);

    private static boolean run = true;

    public static void main(String[] args) {

        configManager.loadConfig();
        checkDirectories();
        loadCommands();

        do {
            System.out.print(">> ");
            commandManager.commandListener(scanner.nextLine());
        } while (run);
        
        scanner.close();
    }

    public static void stopRunning() {
        run = false;
    }

    // Check if directories exists
    private static void checkDirectories() {
        System.out.println("Checking directories...");

        if(!directoryManager.checkDirectory(configManager.getProperty("directory.spreadsheet.root"))) {
            directoryManager.createDirectory(directoryManager.getRootPath(), "spreadsheets");
        }

        System.out.println("Directories checked successfully.");
    }

    // Carrega todos os comandos :D
    private static void loadCommands() {
        commandManager.commandRegister(new HelpCommand());
        commandManager.commandRegister(new CloseCommand());
        commandManager.commandRegister(new SpreadsheetCommand());
        commandManager.commandRegister(new MonthlyRegisterCommand());
        commandManager.commandRegister(new RegistryCommand());
    }
}