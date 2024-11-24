package de.paulmueser;

import org.apache.commons.cli.*;

public class Main {
    private static String inputFilePath;
    private static String outputFilePath = "./result/";
    private static boolean isTraining = false;
    
    public static void main(String[] args) {
        parseArguments(args);
        Executor executor = new Executor(inputFilePath, outputFilePath, isTraining);
        if (isTraining) {
            runTraining(executor);
        } else {
            runPrediction(executor);
        }
        System.exit(0);
    }

    private static void runPrediction(Executor executor) {
        Log.rainbow("Mode", "Prediction");
        
        executor.execute();
    }

    private static void runTraining(Executor executor) {
        Log.rainbow("Mode", "Training");
        executor.execute();
    }

    private static void parseArguments(String[] args) {
        Options options = new Options();
        
        // Add options here
        options.addOption("h", "help", false, "Show available options");
        options.addOption("i", "input", true, "Input file path");
        options.addOption("t", "training", false, "Training mode");
        options.addOption("o", "output", true, "Output file path");
        options.addOption("t", "train", false, "Flag indicating if the data is training data");

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
                System.out.println("Input file path: " + cmd.getOptionValue("i"));
                inputFilePath = cmd.getOptionValue("i");
            } else {
                throw new ParseException("The i (input) option is required");
            }
            
            if (cmd.hasOption("t")) {
                isTraining = true;
            }
            
            if (cmd.hasOption("o")) {
                outputFilePath = cmd.getOptionValue("o");
            }
            
        } catch (ParseException e) {
            Log.e("ArgumentParser", e.getMessage());
            formatter.printHelp("utility-name", options);
            System.exit(1);
        }
    }
}