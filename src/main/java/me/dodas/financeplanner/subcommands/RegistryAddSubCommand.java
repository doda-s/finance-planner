package me.dodas.financeplanner.subcommands;

    import me.dodas.financeplanner.models.Registry;
import me.dodas.financeplanner.models.Revenue;

import java.util.List;
    
    import me.dodas.financeplanner.interfaces.SubCommand;
    import me.dodas.financeplanner.managers.SpreadsheetManager;
import me.dodas.financeplanner.models.Expense;
import me.dodas.financeplanner.models.MonthlyRegister;
    
    import org.apache.commons.cli.CommandLine;
    import org.apache.commons.cli.CommandLineParser;
    import org.apache.commons.cli.DefaultParser;
    import org.apache.commons.cli.HelpFormatter;
    import org.apache.commons.cli.MissingArgumentException;
    import org.apache.commons.cli.Option;
    import org.apache.commons.cli.Options;
    import org.apache.commons.cli.ParseException;

public class RegistryAddSubCommand implements SubCommand{
    
        private String name = "add";
        private String description = "add new registers to the designated monthly register";
        private Options options = new Options();
    
        public RegistryAddSubCommand(){
            options.addOption(
                "h","help",false, "Shows a list with flags information."
            );
            options.addOption(
                Option.builder("t")
                .longOpt("type")
                .hasArg(true)
                .argName("type")
                .desc("Choose between revenue and expenese")
                .required(true)
                .build()
                );
            options.addOption(
                Option.builder("i")
                .hasArg(true)
                .argName("id")
                .desc("Specify the id of monthly register")
                .required(true)
                .build());
            options.addOption(
                Option.builder("d")
                .hasArg(true)
                .argName("description")
                .desc("description of the revenue/expense")
                .required(true)
                .build());
             options.addOption(
                Option.builder("v")
                .hasArg(true)
                .argName("value")
                .desc("value of the revenue/expense")
                .required(true)
                .build());
            options.addOption(
                Option.builder("n")
                .hasArg(true)
                .argName("name")
                .desc("name of the revenue/expense")
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
            String type = null;
            String id = null;
            String name = null;
            String description = null;
            Double value = null;

            try {
    
                CommandLine cmd = parser.parse(options, args.toArray(new String [0]));
                
                if(cmd.hasOption("h")){
                    helpFormatter.printHelp("add revenue or expenses", options);
                }
    
                if(cmd.hasOption("t")){
                    type = cmd.getOptionValue("t");
                }
    
                if(cmd.hasOption("i")){
                    id = cmd.getOptionValue("i");
                }

                if(cmd.hasOption("n")){
                    name = cmd.getOptionValue("n");
                }
                
                if(cmd.hasOption("d")){
                    description = cmd.getOptionValue("d");
                }

                if(cmd.hasOption("v")){
                    value = Double.valueOf(cmd.getOptionValue("v"));
                }


                MonthlyRegister mr =SpreadsheetManager.getInstance().getLoadedSpreadsheet().getMonthlyRegisterById(id);

                if(type.equals("revenue")){
                    Registry revenue = new Revenue(id + mr.getRevenueQuantityValue());
                    revenue.setDescription(description);
                    revenue.setName(name);
                    revenue.setValue(value);
                    mr.addRevenue(revenue);
                    System.out.println("entrou no type");
                }
    
                if(type.equals("expense")){
                    Registry expense = new Expense(id + mr.getExpenseQuantityValue());
                    expense.setDescription(description);
                    expense.setName(name);
                    expense.setValue(value);
                    mr.addRevenue(expense);
                }
    
            } 
            catch (MissingArgumentException mae) {
                // Quando uma opção que requer argumento, recebe um argumento nulo.
                if(mae.getOption().getOpt().equals("t")) {
                    System.out.println("The [-t] flag needs a value <type>");
                }
                if(mae.getOption().getOpt().equals("i")) {
                    System.out.println("The [-i] flag needs a value <id>");
                }
                if(mae.getOption().getOpt().equals("d")) {
                    System.out.println("The [-d] flag needs a value <description>");
                }
                if(mae.getOption().getOpt().equals("v")) {
                    System.out.println("The [-v] flag needs a value <value>");
                }
                if(mae.getOption().getOpt().equals("n")) {
                    System.out.println("The [-n] flag needs a value <namere>");
                }
                System.out.println("Use [-h, --help] to see a help list with all flags.");
    
            } catch (ParseException pe) {
    
                System.out.println("Erro ao processar argumentos: "+ pe.getMessage());
    
            } catch (IllegalStateException ise) {
    
                System.out.println(ise.getMessage());
                
            }
        }
        
    }
    