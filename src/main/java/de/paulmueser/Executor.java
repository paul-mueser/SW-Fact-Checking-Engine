package de.paulmueser;

import de.paulmueser.data.InputDataSet;

public class Executor {
    private final String inputFilePath;
    private final String outputFilePath;
    private final boolean isTrainData;
    private InputDataSet dataSet;

    public Executor(String inputFilePath, String outputFilePath, boolean isTrainData) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
        this.isTrainData = isTrainData;
    }

    public void execute() {
        setup();
        process();
        finish();
    }

    private void setup() {
        this.dataSet = readInputFile();
        //TODO setup input
    }

    private void process() {
        //TODO execute the main logic here
    }

    private void finish() {
        //TODO finish logic
        writeOutputFile();
    }

    private InputDataSet readInputFile() {
        return new InputDataSet(this.inputFilePath, this.isTrainData);
    }

    private void writeOutputFile() {
        this.dataSet.writeOutputFile(this.outputFilePath);
    }

    public String getInputFilePath() {
        return inputFilePath;
    }

    public String getOutputFilePath() {
        return outputFilePath;
    }
}
