package me.dodas.financeplanner.managers;

import com.google.gson.Gson;

import me.dodas.financeplanner.models.Spreadsheet;

public class SpreadsheetManager {

    private static SpreadsheetManager instance = new SpreadsheetManager();
    private FileManager fileManager = FileManager.getInstance();
    private DirectoryManager directoryManager = DirectoryManager.getInstance();
    private ConfigManager configManager = ConfigManager.getInstance();
    private Gson gson = new Gson();
    private Spreadsheet loadedSpreadsheet;
    private String spreadsheetDirectory = String.format("%s/%s", directoryManager.getRootPath(), configManager.getProperty("directory.spreadsheet.root"));

    private SpreadsheetManager() {}

    public static SpreadsheetManager getInstance() {
        return instance;
    }

    public void createSpreadsheet(String spreadsheetName, boolean forceCreation) {    
        if(loadedSpreadsheet != null && !forceCreation) {
            throw new IllegalStateException("A spreadsheet is already loaded. Use [-f, --force] to overwrite it.");
        }

        if(spreadsheetName == null) {
            throw new IllegalStateException("The name is invalid. Use [-n, --sheet_name <name>].");
        }

        String[] fileNames = fileManager.getFilesName(spreadsheetDirectory, new String[]{"json"});
        for(String name : fileNames) {
            if (name.replace(".json", "").equals(spreadsheetName)) {
                throw new IllegalArgumentException(String.format("The file named '%s' already exists.", spreadsheetName));
            }
        }

        loadedSpreadsheet = new Spreadsheet(spreadsheetName);
        fileManager.writeFile(String.format("%s/%s.json", spreadsheetDirectory, spreadsheetName), gson.toJson(loadedSpreadsheet));
    }

    public void loadSpreadsheet(String spreadsheetName, boolean forceLoad) {
        if(loadedSpreadsheet != null && !forceLoad) {
            throw new IllegalStateException("A spreadsheet is already loaded. Use forceCreation to overwrite it.");
        }

        loadedSpreadsheet = gson.fromJson(fileManager.readFile(String.format("%s/%s.json", spreadsheetDirectory, spreadsheetName)), Spreadsheet.class);
    }

    public void closeSpreadsheet() {
        if(loadedSpreadsheet != null) {
            //save
            loadedSpreadsheet = null;
            return;
        }

        throw new NullPointerException("No spreadsheet was loaded.");
    }

    public void saveSpreadsheet() {
        if(loadedSpreadsheet == null) {
            throw new IllegalStateException("No spreadsheet loaded to save.");    
        }

        fileManager.writeFile(String.format("%s/%s.json", spreadsheetDirectory, loadedSpreadsheet.getName()), gson.toJson(loadedSpreadsheet));
        System.out.printf("The spreadsheet '%s' has been saved.", loadedSpreadsheet.getName());
    }

    public Spreadsheet getLoadedSpreadsheet() {
        return loadedSpreadsheet;
    }
}
