package me.dodas.financeplanner.subcommands;

import java.util.List;

import me.dodas.financeplanner.interfaces.SubCommand;
import me.dodas.financeplanner.managers.SpreadsheetManager;
import me.dodas.financeplanner.models.MonthlyRegister;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class MonthlyRegisterRemoveSubCommand implements SubCommand{
    private String name = "remove";
    private String description = "remove revenue or expenses which must be specified.";
    private Options options = new Options();

    public MonthlyRegisterRemoveSubCommand(){
        options.addOption(
            "h","help",false, "Shows a list with flags information."
        );
        options.addOption(
            Option.builder("i")
            .hasArg(true)
            .longOpt("id")
            .argName("id")
            .desc("Specify the id of the monthly register")
            .required(true)
            .build());
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void executeCommand(List<String> args) {
        CommandLineParser parser = new DefaultParser();
        HelpFormatter helpFormatter = new HelpFormatter();
        List<MonthlyRegister> monthlyRegisters = SpreadsheetManager.getInstance().getLoadedSpreadsheet().getMonthlyRegister();
        String id = null;
        Boolean success = null;
        try {

            CommandLine cmd = parser.parse(options, args.toArray(new String [0]));
            
            if(cmd.hasOption("h")){
                helpFormatter.printHelp("remove monthly register", options);
            }

            if(cmd.hasOption("id")){
                id = cmd.getOptionValue("id");
                System.out.println(id);
            }
            
            success = monthlyRegisters.remove(SpreadsheetManager.getInstance().getLoadedSpreadsheet().getMonthlyRegisterById(id));
            if(success){
                System.out.println(id + " removed successfully.");
                return;
            }
            System.out.println(id + " doens't exists.");                

        } 
        catch (MissingArgumentException mae) {
            // Quando uma opção que requer argumento, recebe um argumento nulo.
            if(mae.getOption().getOpt().equals("i")) {
                System.out.println("The [-i] flag needs a value <id>");
            }

            System.out.println("Use [-h, --help] to see a help list with all flags.");

        } catch (ParseException pe) {

            System.out.println("Erro ao processar argumentos: "+ pe.getMessage());

        } catch (IllegalStateException ise) {

            System.out.println(ise.getMessage());
            
        }
    }
    
}
