package me.dodas.financeplanner;

import me.dodas.financeplanner.entities.ConfigManager;
import me.dodas.financeplanner.entities.DirectoryManager;

public class Main {
    static ConfigManager configManager = ConfigManager.getInstance();
    static DirectoryManager directoryManager = DirectoryManager.getInstance();

    public static void main(String[] args) {

        configManager.loadConfig();
        checkDirectories();
        
    }

    // Check if directories exists
    private static void checkDirectories() {
        System.out.println("Checking directories...");

        if(!directoryManager.checkDirectory(configManager.getProperty("directory.spreadsheet.root"))) {
            directoryManager.createDirectory(directoryManager.getRootPath(), "spreadsheets");
        }

        System.out.println("Directories checked successfully.");
    }
}