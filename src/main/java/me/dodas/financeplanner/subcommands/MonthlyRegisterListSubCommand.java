package me.dodas.financeplanner.subcommands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import me.dodas.financeplanner.interfaces.SubCommand;
import me.dodas.financeplanner.managers.SpreadsheetManager;
import me.dodas.financeplanner.models.MonthlyRegister;
import me.dodas.financeplanner.utils.TableFormatter;

public class MonthlyRegisterListSubCommand implements SubCommand{
    private String name = "list";
    private String description = "List all the monthly registers.";
    private Options options = new Options();

    public MonthlyRegisterListSubCommand(){
        options.addOption(
            "h", 
            "help", 
            false, 
            "Shows a list with flags information."
        );
    }
     
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void executeCommand(List<String> args) {
        CommandLineParser clParser = new DefaultParser();
        HelpFormatter helpFormatter = new HelpFormatter();

        try {
            
            CommandLine cmd = clParser.parse(options, args.toArray(new String[0]));
            if(cmd.hasOption("h")){
                helpFormatter.printHelp("list all the registers in this spreadsheet", options);
                return;
            }

            List<String> headers = Arrays.asList("ID", "AMOUNT REVENUES", "AMOUNT EXPENSES");
            List<String[]> rows = new ArrayList<>();

            if(SpreadsheetManager.getInstance().getLoadedSpreadsheet() != null) {
            	for (MonthlyRegister monthlyReg : SpreadsheetManager.getInstance().getLoadedSpreadsheet().getMonthlyRegister()) {
                    // Adiciona uma nova linha, onde cada elemento da String[] representa uma coluna
                    rows.add(new String[]{
                        monthlyReg.getId(), 
                        "R$"+monthlyReg.getTotalAmountRevenues(), 
                        "R$"+monthlyReg.getTotalAmountExpenses()}
                    );          
                }
                TableFormatter tf = new TableFormatter(headers, rows);
                tf.printTable();
            }
            
        }
        catch (ParseException pe) {

            System.out.println("Error processing arguments: "+ pe.getMessage());

        } catch (IllegalArgumentException iae) {

            System.out.println(iae.getMessage());

        }
        
    }

}
