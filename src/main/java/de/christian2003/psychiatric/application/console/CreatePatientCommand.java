package de.christian2003.psychiatric.application.console;

import de.christian2003.psychiatric.adapters.console.Colors;
import de.christian2003.psychiatric.adapters.console.ConsoleWriter;
import de.christian2003.psychiatric.application.services.PatientService;
import de.christian2003.psychiatric.application.services.ServiceException;
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
            ConsoleWriter.println("Missing argument 'firstname'.", Colors.RED);
            return;
        }

        String lastname = null;
        if (args.containsKey("lastname")) {
            lastname = args.get("lastname");
        }
        else {
            ConsoleWriter.println("Missing argument 'lastname'.", Colors.RED);
            return;
        }

        LocalDate birthday = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (args.containsKey("birthday")) {
            try {
                birthday = LocalDate.parse(args.get("birthday"), formatter);
            }
            catch (Exception e) {
                ConsoleWriter.println("Unsupported date format \"" + args.get("birthday") + "\".", Colors.RED);
                return;
            }
        }
        else {
            ConsoleWriter.println("Missing argument 'birthday'.", Colors.RED);
            return;
        }

        UUID cia = null;
        if (args.containsKey("cia")) {
            try {
                cia = UUID.fromString(args.get("cia"));
            }
            catch (Exception e) {
                ConsoleWriter.println("Invalid ID \"" + args.get("cia") + "\".", Colors.RED);
                return;
            }
        }
        else {
            ConsoleWriter.println("Missing argument 'cia'.", Colors.RED);
            return;
        }

        Patient patient;
        try {
            patient = patientService.createPatient(firstname, lastname, birthday, cia);
        }
        catch (ServiceException e) {
            ConsoleWriter.println(e.getMessage(), Colors.RED);
            return;
        }

        ConsoleWriter.println("Created patient .", Colors.DEFAULT);
    }

}
