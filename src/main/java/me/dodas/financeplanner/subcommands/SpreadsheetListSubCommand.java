package me.dodas.financeplanner.subcommands;

import java.util.List;

import com.google.gson.Gson;

import me.dodas.financeplanner.interfaces.SubCommand;
import me.dodas.financeplanner.managers.ConfigManager;
import me.dodas.financeplanner.managers.DirectoryManager;
import me.dodas.financeplanner.managers.FileManager;
import me.dodas.financeplanner.models.Spreadsheet;

public class SpreadsheetListSubCommand implements SubCommand {

    private String name = "list";
    private String description = "List all spreadsheets in directory.";
    private FileManager fileManager = FileManager.getInstance();
    
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void executeCommand(List<String> args) {
        Gson gson = new Gson();
        String spreadsheetsPath = String.format("%s/%s", 
            DirectoryManager.getInstance().getRootPath(), 
            ConfigManager.getInstance().getProperty("directory.spreadsheet.root"
        ));

        String[] filesName = fileManager.getFilesName(spreadsheetsPath, new String[]{".json"});

        for(int i = 0; i < filesName.length; i++) {
            String path = String.format("%s/%s", spreadsheetsPath, filesName[i]);
            Spreadsheet spreadsheet = gson
                .fromJson(fileManager.readFile(path), Spreadsheet.class);
            printItemList(spreadsheet);
        }
    }

    private void printItemList(Spreadsheet item) {
        System.out.print("--------------------------------------------------\n");
        System.out.print(item.getName().toUpperCase()+"\n");
        System.out.print("Creation: "+item.getCreationDate()+"\n");
        System.out.print("Last update: "+item.getLastUpdateDate()+"\n");
        System.out.print("--------------------------------------------------\n");
    }
    
}
