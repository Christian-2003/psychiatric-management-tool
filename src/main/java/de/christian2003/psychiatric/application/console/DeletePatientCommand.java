package de.christian2003.psychiatric.application.console;

import de.christian2003.psychiatric.plugins.console.Colors;
import de.christian2003.psychiatric.plugins.console.ConsoleWriter;
import de.christian2003.psychiatric.application.console.metadata.CommandInfo;
import de.christian2003.psychiatric.application.console.metadata.ParameterInfo;
import de.christian2003.psychiatric.domain.services.PatientService;
import de.christian2003.psychiatric.domain.services.ServiceException;

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


    @Override
    public CommandInfo getInfo() {
        return new CommandInfo(
                "delete patient",
                "Deletes a patient.",
                "delete patient id=abe372e7-a249-4795-a2a6-ffe94a7993ce",
                Map.of(
                        "id", new ParameterInfo(true, "ID of the patient to delete")
                )
        );
    }

}
