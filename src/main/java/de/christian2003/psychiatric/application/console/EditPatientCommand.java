package de.christian2003.psychiatric.application.console;

import de.christian2003.psychiatric.adapters.console.Colors;
import de.christian2003.psychiatric.adapters.console.ConsoleWriter;
import de.christian2003.psychiatric.application.console.metadata.CommandInfo;
import de.christian2003.psychiatric.application.console.metadata.ParameterInfo;
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
                ConsoleWriter.println("Invalid ID \"" + args.get("id") + "\".", Colors.RED);
                return;
            }
        }
        else {
            ConsoleWriter.println("Missing argument 'id'.", Colors.RED);
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
                ConsoleWriter.println("Unsupported date format \"" + args.get("birthday") + "\".", Colors.RED);
                return;
            }
        }

        try {
            patientService.editPatient(id, firstname, lastname, birthday);
        }
        catch (ServiceException e) {
            System.out.println(e.getMessage());
            return;
        }

        ConsoleWriter.println("Updated patient.", Colors.DEFAULT);
    }


    @Override
    public CommandInfo getInfo() {
        return new CommandInfo(
                "edit patient",
                "Edits a patient.",
                "edit patient id=abe372e7-a249-4795-a2a6-ffe94a7993ce firstname=JD lastname=Vance birthday=1984-08-02",
                Map.of(
                        "id", new ParameterInfo(true, "ID of the patient to edit"),
                        "firstname", new ParameterInfo(false, "New first name for the patient"),
                        "lastname", new ParameterInfo(false, "New last name for the patient"),
                        "birthday", new ParameterInfo(false, "New birthday for the patient. This must always be in the format 'yyyy-MM-dd'")
                )
        );
    }

}
