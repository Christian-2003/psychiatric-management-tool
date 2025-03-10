package de.christian2003.psychiatric.adapters.console;

public class ConsoleWriter {

    public static void print(String text, int length, Colors color) {
        color.apply();
        System.out.print(format(text, length));
    }

    public static void println(String text, int length, Colors color) {
        color.apply();
        System.out.println(format(text, length));
    }

    public static void print(String text, Colors color) {
        color.apply();
        System.out.print(text);
    }

    public static void println(String text, Colors color) {
        color.apply();
        System.out.println(text);
    }



    private static String format(String text, int length) {
        if (text.length() > length) {
            return text.substring(0, length - 3) + "...";
        }
        else {
            return String.format("%-" + length + "s", text);
        }
    }

}
