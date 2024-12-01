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
import me.dodas.financeplanner.models.Spreadsheet;

public class MonthlyRegisterCreateSubCommand implements SubCommand {

    private String name = "add";
    private String description = "Add a new monthly register to spreadsheet.";
    private Options options = new Options();

    public MonthlyRegisterCreateSubCommand() {
        options.addOption(
            "h", 
            "help", 
            false, 
            "Shows a list with flags information."
        );
        options.addOption(
            Option.builder("m")
            .longOpt("month")
            .hasArg(true)
            .argName("month_index")
            .desc("Month of the register to be created.")
            .required(false)
            .build()
        );
        options.addOption(
            Option.builder("y")
            .longOpt("year")
            .hasArg(true)
            .argName("year")
            .desc("Year of the register to be created.")
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
        CommandLineParser clParser = new DefaultParser();
        HelpFormatter helpFormatter = new HelpFormatter();
        int month = 0;
        int year = 0;

        try {
            
            CommandLine cmd = clParser.parse(options, args.toArray(new String[0]));

            if(cmd.hasOption("h")) {
                helpFormatter.printHelp("monthly-register add", options);
                return;
            }

            if (cmd.hasOption("m")) {
                month = Integer.valueOf(cmd.getOptionValue("m"));
            }

            if (cmd.hasOption("y")) {
                year = Integer.valueOf(cmd.getOptionValue("y"));
            }

            Spreadsheet sheet = SpreadsheetManager
                .getInstance()
                .getLoadedSpreadsheet();
            
            if(sheet == null) {
                System.out.println("No spreadsheet was loaded.");
                return;
            }

            sheet.addMonthlyRegister(month, year);

        } catch (MissingArgumentException mae) {
            if (mae.getOption().getOpt().equals("m")) {
                System.out.println("The [-m, --month] flag needs a value <month_index>.");
            }

            if (mae.getOption().getOpt().equals("y")) {
                System.out.println("The [-y, --year] flag needs a value <year>.");
            }

            System.out.println("Use [-h, --help] to see a help list with all flags.");
        } catch (ParseException pe) {

            System.out.println("Error processing arguments: "+ pe.getMessage());

        } catch (IllegalArgumentException iae) {

            System.out.println(iae.getMessage());

        }
    }
    
}
