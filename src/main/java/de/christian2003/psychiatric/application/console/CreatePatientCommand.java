package de.christian2003.psychiatric.application.console;

import de.christian2003.psychiatric.plugins.console.Colors;
import de.christian2003.psychiatric.plugins.console.ConsoleWriter;
import de.christian2003.psychiatric.application.console.metadata.CommandInfo;
import de.christian2003.psychiatric.application.console.metadata.ParameterInfo;
import de.christian2003.psychiatric.application.services.PatientService;
import de.christian2003.psychiatric.application.services.ServiceException;
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

        try {
            patientService.createPatient(firstname, lastname, birthday, cia);
        }
        catch (ServiceException e) {
            ConsoleWriter.println(e.getMessage(), Colors.RED);
            return;
        }

        ConsoleWriter.println("Created patient.", Colors.DEFAULT);
    }


    @Override
    public CommandInfo getInfo() {
        return new CommandInfo(
                "create patient",
                "Creates a new patient.",
                "create patient firstname=Donald lastname=Trump birthday=1946-06-14 cia=abe372e7-a249-4795-a2a6-ffe94a7993ce",
                Map.of(
                        "firstname", new ParameterInfo(true, "First name of the patient"),
                        "lastname", new ParameterInfo(true, "Last name of the patient"),
                        "birthday", new ParameterInfo(true, "Birthday of the patient. This must always be in the format 'yyyy-MM-dd'"),
                        "cia", new ParameterInfo(true, "ID of the crisis intervention area for the patient")
                )
        );
    }

}
