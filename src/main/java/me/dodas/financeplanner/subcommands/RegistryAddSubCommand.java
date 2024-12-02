package me.dodas.financeplanner.subcommands;

    import me.dodas.financeplanner.models.Registry;
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
    
            try {
    
                CommandLine cmd = parser.parse(options, args.toArray(new String [0]));
                
                if(cmd.hasOption("h")){
                    helpFormatter.printHelp("remove revenue or expenses", options);
                }
    
                if(cmd.hasOption("t")){
                    type = cmd.getOptionValue("t");
                }
    
                if(cmd.hasOption("id")){
                    id = cmd.getOptionValue("id");
                }
    
                if(type == "revenue"){
                    for (MonthlyRegister monthlyReg : monthlyRegister) {
                        List<Registry> revenues = monthlyReg.getRevenues();
                        revenues.remove(monthlyReg.getRevenueById(id));
                    }
                }
    
                if(type == "expense"){
                    for (MonthlyRegister monthlyReg : monthlyRegister) {
                        List<Registry> expenses = monthlyReg.getExpenses();
                        expenses.remove(monthlyReg.getExpensesById(id));
                    }
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
    
                System.out.println("Use [-h, --help] to see a help list with all flags.");
    
            } catch (ParseException pe) {
    
                System.out.println("Erro ao processar argumentos: "+ pe.getMessage());
    
            } catch (IllegalStateException ise) {
    
                System.out.println(ise.getMessage());
                
            }
        }
        
    }
    