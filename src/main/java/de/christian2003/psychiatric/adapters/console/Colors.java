package de.christian2003.psychiatric.adapters.console;

public enum Colors {

    DEFAULT("\u001B[0m"),
    BLACK("\u001B[30m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m"),
    CYAN("\u001B[36m"),
    WHITE("\u001B[37m");


    private final String escapeCode;


    Colors(String escapeCode) {
        this.escapeCode = escapeCode;
    }


    public void apply() {
        System.out.print(escapeCode);
    }

}
