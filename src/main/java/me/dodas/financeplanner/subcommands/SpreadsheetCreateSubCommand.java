package me.dodas.financeplanner.subcommands;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import me.dodas.financeplanner.entities.SpreadsheetManager;
import me.dodas.financeplanner.interfaces.SubCommand;

public class SpreadsheetCreateSubCommand implements SubCommand {

    private String name = "create";
    private String description = "Create a spreadsheet.";
    Options options = new Options();

    public SpreadsheetCreateSubCommand() {
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
            "Force the spreadseet creation. It can overwrite an already loaded spreadsheet."
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

            SpreadsheetManager.getInstance().createSpreadsheet(spreadsheetName, force);

        } catch (MissingOptionException moe) {
            // Quando uma opção obrigatória não é encontrada.
            if(moe.getMissingOptions().contains("n")) {
                System.out.println("The [-n] flag is missing.");
            }

            System.out.println("Use [-h, --help] to see a help list with all flags.");

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
            
        } catch (IllegalArgumentException iae) {

            System.out.println(iae.getMessage());

        }

        

    }
    
}
