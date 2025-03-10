package de.christian2003.psychiatric.application.console;

import de.christian2003.psychiatric.application.services.CrisisInterventionAreaService;
import de.christian2003.psychiatric.application.services.PatientService;
import de.christian2003.psychiatric.application.services.ServiceException;
import de.christian2003.psychiatric.domain.rooms.CrisisInterventionArea;

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
                System.out.println("Invalid ID for patient \"" + args.get("patient") + "\".");
                return;
            }
        }
        else {
            System.out.println("Missing argument 'patient'.");
            return;
        }

        UUID cia = null;
        if (args.containsKey("cia")) {
            try {
                cia = UUID.fromString(args.get("cia"));
            }
            catch (IllegalArgumentException e) {
                System.out.println("Invalid ID for crisis intervention area \"" + args.get("cia") + "\".");
                return;
            }
        }
        else {
            System.out.println("Missing argument 'cia'.");
            return;
        }

        try {
            patientService.movePatientToCrisisInterventionArea(patient, cia);
        }
        catch (ServiceException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Moved patient \"" + patient + "\" to crisis intervention area \"" + cia + "\".");
    }

}
