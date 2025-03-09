package de.christian2003.psychiatric.application.console;

import de.christian2003.psychiatric.application.services.PatientService;
import de.christian2003.psychiatric.domain.people.Patient;
import de.christian2003.psychiatric.domain.people.PersonalData;

import java.util.List;
import java.util.Map;

public class ListPatientsCommand implements Command {

    private final PatientService patientService;


    public ListPatientsCommand(PatientService patientService) throws NullPointerException {
        if (patientService == null) {
            throw new NullPointerException();
        }
        this.patientService = patientService;
    }


    @Override
    public void execute(Map<String, String> args) {
        if (!args.isEmpty()) {
            System.out.println("Invalid arguments.");
            return;
        }

        List<Patient> patients = patientService.getAllPatients();
        for (Patient patient: patients) {
            System.out.println(patient);
        }
        if (patients.isEmpty()) {
            System.out.println("No patients available.");
        }
    }

}
