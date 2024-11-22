package de.paulmueser;

public class Executor {
    private final String inputFilePath;
    private final String outputFilePath;

    public Executor(String inputFilePath, String outputFilePath) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
    }

    public void execute() {
        setup();
        process();
        finish();
    }

    private void setup() {
        readInputFile();
        //TODO setup input
    }

    private void process() {
        //TODO execute the main logic here
    }

    private void finish() {
        //TODO finish logic
        writeOutputFile();
    }

    private void readInputFile() {
        //TODO read input file
    }

    private void writeOutputFile() {
        //TODO write output file
    }

    public String getInputFilePath() {
        return inputFilePath;
    }

    public String getOutputFilePath() {
        return outputFilePath;
    }
}
