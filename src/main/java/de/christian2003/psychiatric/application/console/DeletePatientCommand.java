package de.christian2003.psychiatric.application.console;

import de.christian2003.psychiatric.adapters.console.Colors;
import de.christian2003.psychiatric.adapters.console.ConsoleWriter;
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
                ConsoleWriter.println("Invalid ID \"" + args.get("id") + "\".", Colors.RED);
                return;
            }
        }
        else {
            ConsoleWriter.println("Missing argument 'id'.", Colors.RED);
            return;
        }

        try {
            patientService.deletePatient(id);
        }
        catch (ServiceException e){
            ConsoleWriter.println(e.getMessage(), Colors.RED);
            return;
        }

        ConsoleWriter.println("Deleted patient.", Colors.DEFAULT);
    }

}
