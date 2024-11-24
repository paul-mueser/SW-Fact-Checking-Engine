package de.paulmueser;

public class Log {

    /**
     * Basic ANSI color codes
     */
    private static final String[] ansiColors = {
            "\u001B[31m",  // Red
            "\u001B[33m",  // Yellow
            "\u001B[32m",  // Green
            "\u001B[36m",  // Cyan
            "\u001B[34m",  // Blue
            "\u001B[35m",  // Magenta
    };

    /**
     * ANSI escape code to reset text color to default
     */
    private static final String resetColorCode = "\u001B[0m";


    /**
     * Prints a log in the console
     *
     * @param tag tag of the log
     * @param msg message of the log
     */
    public static void d(String tag, String msg) {
        System.out.println(tag + " : " + msg);
    }

    /**
     * Prints a red error log in the console
     *
     * @param tag tag of the log
     * @param msg message of the log
     */
    public static void e(String tag, String msg) {
        // ANSI escape code for red text
        String redColorCode = ansiColors[0];

        String text = tag + " : " + msg;

        printInColor(text, redColorCode);
    }

    /**
     * Prints a yellow warning log in the console
     *
     * @param tag tag of the log
     * @param msg message of the log
     */
    public static void w(String tag, String msg) {
        // ANSI escape code for red text
        String yellowColor = ansiColors[1];

        String text = tag + " : " + msg;

        printInColor(text, yellowColor);
    }

    /**
     * Prints a green success log in the console
     *
     * @param tag tag of the log
     * @param msg message of the log
     */
    public static void s(String tag, String msg) {
        // ANSI escape code for red text
        String greenColor = ansiColors[2];

        String text = tag + " : " + msg;

        printInColor(text, greenColor);
    }

    /**
     * Prints a rainbow-colored log in the console
     *
     * @param tag tag of the log
     * @param msg message of the log
     */
    public static void rainbow(String tag, String msg) {
        // Your rainbow text
        String rainbowText = tag + " : " + msg;

        // Print rainbow text to the console
        for (int i = 0; i < rainbowText.length(); i++) {
            char   currentChar  = rainbowText.charAt(i);
            int    colorIndex   = i % ansiColors.length;
            String currentColor = ansiColors[colorIndex];
            System.out.print(currentColor + currentChar);
        }

        System.out.println(resetColorCode);
    }


    private static void printInColor(String msg, String color) {
        System.out.println(color + msg + resetColorCode);
    }
}
