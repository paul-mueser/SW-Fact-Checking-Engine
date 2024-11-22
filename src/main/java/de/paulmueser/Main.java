package de.paulmueser;

import org.apache.commons.cli.*;

public class Main {
    private static String inputFilePath;
    private static String outputFilePath = "./result/";
    
    public static void main(String[] args) {
        parseArguments(args);
    }

    private static void parseArguments(String[] args) {
        Options options = new Options();
        
        // Add options here
        options.addOption("h", "help", false, "Show available options");
        options.addOption("i", "input", true, "Input file path");
        options.addOption("o", "output", true, "Output file path");

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        
        try {
            // TODO: replace utility-name with the name to execute the program
            CommandLine cmd = parser.parse(options, args);
            
            if (cmd.hasOption("h")) {
                formatter.printHelp("utility-name", options);
                System.exit(0);
            }
            
            if (cmd.hasOption("i")) {
                inputFilePath = cmd.getOptionValue("i");
            } else {
                throw new ParseException("The i (input) option is required");
            }
            
            if (cmd.hasOption("o")) {
                outputFilePath = cmd.getOptionValue("o");
            }

            Executor executor = new Executor(inputFilePath, outputFilePath);
            executor.execute();
            
        } catch (ParseException e) {
            Log.e("ArgumentParser", e.getMessage());
            formatter.printHelp("utility-name", options);
            System.exit(1);
        }
    }
}