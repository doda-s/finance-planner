package me.dodas.financeplanner.managers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import me.dodas.financeplanner.models.MonthlyRegister;
import me.dodas.financeplanner.models.Spreadsheet;
import me.dodas.financeplanner.utils.MonthlyRegisterAdapter;

public class SpreadsheetManager {

    private static SpreadsheetManager instance = new SpreadsheetManager();
    private FileManager fileManager = FileManager.getInstance();
    private DirectoryManager directoryManager = DirectoryManager.getInstance();
    private ConfigManager configManager = ConfigManager.getInstance();
    private Gson gson = new GsonBuilder().registerTypeAdapter(MonthlyRegister.class, new MonthlyRegisterAdapter()).create();
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
            throw new IllegalStateException("A spreadsheet is already loaded. Use [-f, --force] to overwrite it.");
        }

        if(spreadsheetName == null) {
            throw new IllegalStateException("The name is invalid. Use [-n, --sheet_name <name>].");
        }

        String path = String.format("%s/%s.json", spreadsheetDirectory, spreadsheetName);

        if(!fileManager.checkFile(path)) {
            throw new IllegalArgumentException(String.format("No spreadsheet named '%s' was found", spreadsheetName));
        }

        loadedSpreadsheet = gson.fromJson(fileManager.readFile(path), Spreadsheet.class);
    }

    public void closeSpreadsheet() {
        if(loadedSpreadsheet != null) {
            saveSpreadsheet();
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
        System.out.printf("The spreadsheet '%s' has been saved.\n", loadedSpreadsheet.getName());
    }
    
    public void deleteSpreadsheet(String spreadsheetName) {
        if(spreadsheetName == null) {
            throw new IllegalStateException("The name is invalid. Use [-n, --sheet_name <name>].");
        }

        fileManager.deleteFile(String.format("%s/%s.json", spreadsheetDirectory, spreadsheetName));
        loadedSpreadsheet = null;

    }

    public Spreadsheet getLoadedSpreadsheet() {
    	if(loadedSpreadsheet != null) {
            return loadedSpreadsheet;	
    	}
    	return null;
    }

    public Spreadsheet getSpreadsheetByName(String name) {
        String path = String.format("%s/%s", spreadsheetDirectory, name+".json");
        if (fileManager.checkFile(path)) {
            Spreadsheet sheet = gson.fromJson(fileManager.readFile(path), Spreadsheet.class);
            return sheet;
        }
        return null;
    }
}
