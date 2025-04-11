package de.christian2003.psychiatric.application.console;

import de.christian2003.psychiatric.plugins.console.Colors;
import de.christian2003.psychiatric.plugins.console.ConsoleWriter;
import de.christian2003.psychiatric.application.console.metadata.CommandInfo;
import de.christian2003.psychiatric.application.console.metadata.ParameterInfo;
import de.christian2003.psychiatric.application.services.PatientService;
import de.christian2003.psychiatric.application.services.ServiceException;

import java.util.Map;
import java.util.UUID;

public class MovePatientCommand implements Command {

    private final PatientService patientService;


    public MovePatientCommand(PatientService patientService) throws NullPointerException {
        if (patientService == null) {
            throw new NullPointerException();
        }
        this.patientService = patientService;
    }


    @Override
    public void execute(Map<String, String> args) {
        UUID patient = null;
        if (args.containsKey("patient")) {
            try {
                patient = UUID.fromString(args.get("patient"));
            }
            catch (IllegalArgumentException e) {
                ConsoleWriter.println("Invalid ID for patient \"" + args.get("patient") + "\".", Colors.RED);
                return;
            }
        }
        else {
            ConsoleWriter.println("Missing argument 'patient'.", Colors.RED);
            return;
        }

        UUID cia = null;
        if (args.containsKey("cia")) {
            try {
                cia = UUID.fromString(args.get("cia"));
            }
            catch (IllegalArgumentException e) {
                ConsoleWriter.println("Invalid ID for crisis intervention area \"" + args.get("cia") + "\".", Colors.RED);
                return;
            }
        }
        else {
            ConsoleWriter.println("Missing argument 'cia'.", Colors.RED);
            return;
        }

        try {
            patientService.movePatientToCrisisInterventionArea(patient, cia);
        }
        catch (ServiceException e) {
            ConsoleWriter.println(e.getMessage(), Colors.RED);
            return;
        }

        ConsoleWriter.println("Moved patient to crisis intervention area.", Colors.DEFAULT);
    }


    @Override
    public CommandInfo getInfo() {
        return new CommandInfo(
                "move patient",
                "Moves a patient to another crisis intervention area.",
                "move patient patient=f10eb3da-ce7f-4d8e-b9c2-bebd9479575e cia=85f4d0a2-116c-481a-aef8-de6eaa9848dd",
                Map.of(
                        "patient", new ParameterInfo(true, "ID of the patient to move"),
                        "cia", new ParameterInfo(true, "ID of the crisis intervention area")
                )
        );
    }

}
