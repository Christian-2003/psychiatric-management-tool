package de.christian2003.psychiatric.adapters.console;

import de.christian2003.psychiatric.application.console.Command;
import de.christian2003.psychiatric.application.console.CreatePatientCommand;
import de.christian2003.psychiatric.application.services.CrisisInterventionAreaService;
import de.christian2003.psychiatric.application.services.NurseService;
import de.christian2003.psychiatric.application.services.OfficeService;
import de.christian2003.psychiatric.application.services.PatientService;
import java.util.HashMap;
import java.util.Map;


public class CommandRegistry {

    private final Map<String, Command> commands;


    public CommandRegistry(PatientService patientService, NurseService nurseService, OfficeService officeService, CrisisInterventionAreaService crisisInterventionAreaService) throws NullPointerException {
        if (patientService == null || nurseService == null || officeService == null || crisisInterventionAreaService == null) {
            throw new NullPointerException();
        }

        commands = new HashMap<>();

        commands.put("create_patient", new CreatePatientCommand(patientService));
    }


    public Command getCommand(String name) {
        if (name == null) {
            return null;
        }
        return commands.get(name);
    }

}
