package me.dodas.financeplanner.entities;

import java.nio.channels.AlreadyBoundException;

import com.google.gson.Gson;

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
            throw new AlreadyBoundException();
        }

        String[] fileNames = fileManager.getFilesName(spreadsheetDirectory, new String[]{"json"});
        for(String name : fileNames) {
            if (name.replace(".json", "") == spreadsheetName) {
                throw new IllegalArgumentException(String.format("The file named %s already exists.", spreadsheetName));
            }
        }

        loadedSpreadsheet = new Spreadsheet(spreadsheetName);
        fileManager.createFile(spreadsheetDirectory, gson.toJson(loadedSpreadsheet));
    }

    public void loadSpreadsheet(String name) {
        
    }

    public void closeSpreadsheet() {
        if(loadedSpreadsheet != null) {
            //save
            loadedSpreadsheet = null;
            return;
        }

        throw new NullPointerException("loadedSpreadsheet is null.");
    }


}
