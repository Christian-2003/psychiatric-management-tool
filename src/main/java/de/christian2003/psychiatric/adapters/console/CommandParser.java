package de.christian2003.psychiatric.adapters.console;

import de.christian2003.psychiatric.application.console.Command;
import java.util.HashMap;
import java.util.Map;


public class CommandParser {

    private final CommandRegistry commandRegistry;


    public CommandParser(CommandRegistry commandRegistry) throws NullPointerException {
        if (commandRegistry == null) {
            throw new NullPointerException();
        }
        this.commandRegistry = commandRegistry;
    }


    public void parseAndExecute(String input) throws NullPointerException {
        if (input == null) {
            throw new NullPointerException();
        }

        String[] parts = input.split(" ");
        if (parts.length < 2) {
            System.out.println("Invalid command \"" + input + "\".");
            return;
        }

        String commandName = parts[0] + "_" + parts[1];
        Command command = commandRegistry.getCommand(commandName);
        if (command == null) {
            System.out.println("Unknown command \"" + input + "\".");
            return;
        }

        Map<String, String> args = new HashMap<>();
        for (int i = 2; i < parts.length; i++) {
            String[] arg = parts[i].split("=");
            if (arg.length == 2) {
                args.put(arg[0], arg[1]);
            }
            else {
                System.out.println("Invalid argument \"" + parts[i] + "\". Arguments must always be of format 'key=value'.");
                return;
            }
        }

        command.execute(args);
    }

}
