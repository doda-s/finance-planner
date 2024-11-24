package me.dodas.financeplanner.subcommands;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import me.dodas.financeplanner.interfaces.SubCommand;
import me.dodas.financeplanner.managers.SpreadsheetManager;

public class SpreadsheetLoadSubCommand implements SubCommand {

    private String name = "load";
    private String description = "Load a spreadsheet based on JSON file.";
    Options options = new Options();

    public SpreadsheetLoadSubCommand() {
        options.addOption(
            "h", 
            "help", 
            false, 
            "Shows a list with flags information."
        );
        options.addOption(
            "f", 
            "force", 
            false, 
            "Force the spreadseet load. It can overwrite an already loaded spreadsheet."
        );
        options.addOption(
            Option.builder("n")
            .longOpt("sheet_name")
            .hasArg(true)
            .argName("name")
            .desc("The spreadsheet name flag.")
            .required(false)
            .build()
        );
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void executeCommand(List<String> args) {
        CommandLineParser parser = new DefaultParser();
        HelpFormatter helpFormatter = new HelpFormatter();

        boolean force = false;
        String spreadsheetName= null;

        try {

            CommandLine cmd = parser.parse(options, args.toArray(new String[0]));
            
            if(cmd.hasOption("h")) {
                helpFormatter.printHelp("spreadsheet create", options);
                return;
            }

            if(cmd.hasOption("f")) {
                force = true;
            }

            if(cmd.hasOption("n")) {
                spreadsheetName = cmd.getOptionValue("n");
            }

            SpreadsheetManager.getInstance().loadSpreadsheet(spreadsheetName, force);

        } catch (MissingArgumentException mae) {
            // Quando uma opção que requer argumento, recebe um argumento nulo.
            if(mae.getOption().getOpt().equals("n")) {
                System.out.println("The [-n] flag needs a value <name>");
            }

            System.out.println("Use [-h, --help] to see a help list with all flags.");

        } catch (ParseException pe) {

            System.out.println("Erro ao processar argumentos: "+ pe.getMessage());

        } catch (IllegalStateException ise) {

            System.out.println(ise.getMessage());

        }
    }
    
}
