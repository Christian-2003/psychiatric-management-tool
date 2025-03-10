package de.christian2003.psychiatric.adapters.console;

import de.christian2003.psychiatric.application.services.CrisisInterventionAreaService;
import de.christian2003.psychiatric.application.services.NurseService;
import de.christian2003.psychiatric.application.services.PatientService;
import java.util.Scanner;


public class ConsoleAdapter {

    private final CommandParser commandParser;


    public ConsoleAdapter(PatientService patientService, NurseService nurseService, CrisisInterventionAreaService crisisInterventionAreaService) throws NullPointerException {
        if (patientService == null || nurseService == null || crisisInterventionAreaService == null) {
            throw new NullPointerException();
        }
        CommandRegistry commandRegistry = new CommandRegistry(patientService, nurseService, crisisInterventionAreaService);
        commandParser = new CommandParser(commandRegistry);
    }


    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("close")) {
                break;
            }

            commandParser.parseAndExecute(input);
        }
        scanner.close();
    }

}
