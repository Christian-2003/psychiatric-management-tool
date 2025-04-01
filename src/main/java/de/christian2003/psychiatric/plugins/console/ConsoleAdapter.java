package de.christian2003.psychiatric.plugins.console;

import de.christian2003.psychiatric.domain.services.CrisisInterventionAreaService;
import de.christian2003.psychiatric.domain.services.PatientService;
import java.util.Scanner;


public class ConsoleAdapter {

    private final CommandParser commandParser;


    public ConsoleAdapter(PatientService patientService, CrisisInterventionAreaService crisisInterventionAreaService) throws NullPointerException {
        if (patientService == null || crisisInterventionAreaService == null) {
            throw new NullPointerException();
        }
        CommandRegistry commandRegistry = new CommandRegistry(patientService, crisisInterventionAreaService);
        commandParser = new CommandParser(commandRegistry);
    }


    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            ConsoleWriter.println("", Colors.DEFAULT);
            ConsoleWriter.print("> ", Colors.DEFAULT);
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("close")) {
                break;
            }

            commandParser.parseAndExecute(input);
        }
        scanner.close();
    }

}
