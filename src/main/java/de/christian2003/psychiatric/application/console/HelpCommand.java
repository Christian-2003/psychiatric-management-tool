package de.christian2003.psychiatric.application.console;

import de.christian2003.psychiatric.adapters.console.Colors;
import de.christian2003.psychiatric.adapters.console.ConsoleWriter;
import de.christian2003.psychiatric.application.console.metadata.CommandInfo;
import de.christian2003.psychiatric.application.console.metadata.ParameterInfo;

import java.util.Map;

public class HelpCommand implements Command {

    private final Map<String, Command> commands;


    public HelpCommand(Map<String, Command> commands) throws NullPointerException {
        if (commands == null) {
            throw new NullPointerException();
        }
        this.commands = commands;
    }


    @Override
    public void execute(Map<String, String> args) {
        ConsoleWriter.println("Available commands:", Colors.DEFAULT);

        for (Command command : commands.values()) {
            CommandInfo info = command.getInfo();
            ConsoleWriter.println("", Colors.DEFAULT);
            ConsoleWriter.print(info.getName(), Colors.YELLOW);
            ConsoleWriter.println(": " + info.getDescription(), Colors.DEFAULT);

            //Display parameters:
            if (info.getParameters().isEmpty()) {
                ConsoleWriter.println("  No parameters required.", Colors.WHITE);
            }
            else {
                ConsoleWriter.println("  Parameters:", Colors.DEFAULT);
                for (Map.Entry<String, ParameterInfo> parameter : info.getParameters().entrySet()) {
                    String required = parameter.getValue().isRequired() ? "(required)" : "(optional)";
                    ConsoleWriter.print("    - ", Colors.DEFAULT);
                    ConsoleWriter.print(parameter.getKey(), 12, Colors.YELLOW);
                    ConsoleWriter.print(" ", Colors.DEFAULT);
                    ConsoleWriter.print(required, Colors.WHITE);
                    ConsoleWriter.print(": ", Colors.DEFAULT);
                    ConsoleWriter.println(parameter.getValue().getDescription(), Colors.DEFAULT);
                }
                ConsoleWriter.print("  Example: ", Colors.DEFAULT);
                ConsoleWriter.println(info.getExample(), Colors.DEFAULT);
            }
        }
    }


    @Override
    public CommandInfo getInfo() {
        return new CommandInfo(
                "help",
                "Displays information for all commands.",
                "help",
                Map.of()
        );
    }

}
