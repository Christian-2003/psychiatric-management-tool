package de.christian2003.psychiatric.adapters.console;

import de.christian2003.psychiatric.application.console.*;
import de.christian2003.psychiatric.application.services.CrisisInterventionAreaService;
import de.christian2003.psychiatric.application.services.PatientService;
import java.util.HashMap;
import java.util.Map;


public class CommandRegistry {

    private final Map<String, Command> commands;


    public CommandRegistry(PatientService patientService, CrisisInterventionAreaService crisisInterventionAreaService) throws NullPointerException {
        if (patientService == null || crisisInterventionAreaService == null) {
            throw new NullPointerException();
        }

        commands = new HashMap<>();

        commands.put("create_patient", new CreatePatientCommand(patientService));
        commands.put("list_patients", new ListPatientsCommand(patientService));
        commands.put("delete_patient", new DeletePatientCommand(patientService));
        commands.put("edit_patient", new EditPatientCommand(patientService));
        commands.put("move_patient", new MovePatientCommand(patientService));

        commands.put("create_cia", new CreateCiaCommand(crisisInterventionAreaService));
        commands.put("list_cias", new ListCiasCommand(crisisInterventionAreaService));
        commands.put("delete_cia", new DeleteCiaCommand(crisisInterventionAreaService));
        commands.put("edit_cia", new EditCiaCommand(crisisInterventionAreaService));

        commands.put("help", new HelpCommand(commands));
    }


    public Command getCommand(String name) {
        if (name == null) {
            return null;
        }
        return commands.get(name);
    }

}
