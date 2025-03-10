package de.christian2003.psychiatric.application.console;

import de.christian2003.psychiatric.application.services.PatientService;
import de.christian2003.psychiatric.application.services.ServiceException;
import de.christian2003.psychiatric.domain.people.Patient;

import java.util.Map;
import java.util.UUID;

public class DeletePatientCommand implements Command {

    private final PatientService patientService;


    public DeletePatientCommand(PatientService patientService) throws NullPointerException {
        if (patientService == null) {
            throw new NullPointerException();
        }
        this.patientService = patientService;
    }


    @Override
    public void execute(Map<String, String> args) {
        UUID id = null;
        if (args.containsKey("id")) {
            try {
                id = UUID.fromString(args.get("id"));
            }
            catch (IllegalArgumentException e) {
                System.out.println("Invalid ID \"" + args.get("id") + "\".");
                return;
            }
        }
        else {
            System.out.println("Missing argument 'id'.");
            return;
        }

        Patient patient;
        try {
            patient = patientService.deletePatient(id);
        }
        catch (ServiceException e){
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Deleted patient " + patient + ".");
    }

}
