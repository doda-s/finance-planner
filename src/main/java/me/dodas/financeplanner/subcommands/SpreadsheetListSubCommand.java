package me.dodas.financeplanner.subcommands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.dodas.financeplanner.interfaces.SubCommand;
import me.dodas.financeplanner.managers.ConfigManager;
import me.dodas.financeplanner.managers.DirectoryManager;
import me.dodas.financeplanner.managers.FileManager;
import me.dodas.financeplanner.managers.SpreadsheetManager;
import me.dodas.financeplanner.models.Spreadsheet;
import me.dodas.financeplanner.utils.TableFormatter;

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
        String spreadsheetsPath = String.format("%s/%s", 
            DirectoryManager.getInstance().getRootPath(), 
            ConfigManager.getInstance().getProperty("directory.spreadsheet.root"
        ));

        String[] filesName = fileManager.getFilesName(spreadsheetsPath, new String[]{".json"});
        List<String> header = Arrays.asList("NAME", "CREATION DATE", "LAST UPDATE");
        List<String[]> rows = new ArrayList<>();

        for(String name : filesName) {
            Spreadsheet sheet = SpreadsheetManager
                .getInstance()
                .getSpreadsheetByName(name.replace(".json", ""));
                
            // Adiciona uma nova linha, onde cada elemento da String[] representa uma coluna
            rows.add(
                new String[]{
                    sheet.getName().toUpperCase(), 
                    sheet.getCreationDate(), sheet.getLastUpdateDate()
                }
            );
        }
        TableFormatter tf = new TableFormatter(header, rows);
        tf.printTable();
    }
    
}
