package de.christian2003.psychiatric.application.console;

import de.christian2003.psychiatric.application.services.PatientService;
import de.christian2003.psychiatric.application.services.ServiceException;
import de.christian2003.psychiatric.domain.people.Patient;
import de.christian2003.psychiatric.domain.people.PersonalData;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.UUID;


public class EditPatientCommand implements Command {

    private final PatientService patientService;


    public EditPatientCommand(PatientService patientService) throws NullPointerException {
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

        String firstname = null;
        if (args.containsKey("firstname")) {
            firstname = args.get("firstname");
        }

        String lastname = null;
        if (args.containsKey("lastname")) {
            lastname = args.get("lastname");
        }

        LocalDate birthday = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (args.containsKey("birthday")) {
            try {
                birthday = LocalDate.parse(args.get("birthday"), formatter);
            }
            catch (Exception e) {
                System.out.println("Unsupported date format \"" + args.get("birthday") + "\".");
                return;
            }
        }

        Patient patient;
        try {
            patient = patientService.editPatient(id, firstname, lastname, birthday);
        }
        catch (ServiceException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Updated patient " + patient + ".");
    }

}
