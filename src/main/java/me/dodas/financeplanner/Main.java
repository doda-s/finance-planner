package me.dodas.financeplanner;

import java.util.Scanner;

import me.dodas.financeplanner.commands.HelpCommand;
import me.dodas.financeplanner.entities.CommandManager;
import me.dodas.financeplanner.entities.ConfigManager;
import me.dodas.financeplanner.entities.DirectoryManager;

public class Main {
    private static ConfigManager configManager = ConfigManager.getInstance();
    private static DirectoryManager directoryManager = DirectoryManager.getInstance();
    private static CommandManager commandManager = CommandManager.getInstance();
    private static Scanner scanner = new Scanner(System.in);

    private static Boolean run = true;

    public static void main(String[] args) {

        configManager.loadConfig();
        checkDirectories();
        loadCommands();

        do {
            commandManager.commandListener(scanner.nextLine());
        } while (run);
        
        scanner.close();
    }

    public void stopRunning() {
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

    private static void loadCommands() {
        commandManager.commandRegister(new HelpCommand());
    }
}