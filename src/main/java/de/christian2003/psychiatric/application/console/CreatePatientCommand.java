package de.christian2003.psychiatric.application.console;

import de.christian2003.psychiatric.application.services.PatientService;
import de.christian2003.psychiatric.domain.people.Patient;
import de.christian2003.psychiatric.domain.people.PersonalData;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.UUID;


public class CreatePatientCommand implements Command {

    private final PatientService patientService;


    public CreatePatientCommand(PatientService patientService) throws NullPointerException {
        if (patientService == null) {
            throw new NullPointerException();
        }
        this.patientService = patientService;
    }


    @Override
    public void execute(Map<String, String> args) {
        String firstname = null;
        if (args.containsKey("firstname")) {
            firstname = args.get("firstname");
        }
        else {
            System.err.println("Missing argument 'firstname'.");
            return;
        }

        String lastname = null;
        if (args.containsKey("lastname")) {
            lastname = args.get("lastname");
        }
        else {
            System.err.println("Missing argument 'lastname'.");
            return;
        }

        LocalDate birthday = null;
        if (args.containsKey("birthday")) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                birthday = LocalDate.parse(args.get("birthday"), formatter);
            }
            catch (Exception e) {
                System.err.println("Unsupported date format \"" + args.get("birthday") + "\".");
                return;
            }
        }
        else {
            System.err.println("Missing argument 'birthday'.");
            return;
        }

        PersonalData personalData = new PersonalData(firstname, lastname, birthday);
        Patient patient = new Patient(UUID.randomUUID(), personalData);
        patientService.createPatient(patient);
    }

}
