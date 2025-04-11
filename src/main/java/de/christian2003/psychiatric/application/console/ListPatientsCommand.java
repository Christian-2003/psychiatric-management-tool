package de.christian2003.psychiatric.application.console;

import de.christian2003.psychiatric.plugins.console.Colors;
import de.christian2003.psychiatric.plugins.console.ConsoleWriter;
import de.christian2003.psychiatric.application.console.metadata.CommandInfo;
import de.christian2003.psychiatric.application.services.PatientService;
import de.christian2003.psychiatric.domain.people.Patient;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
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
            ConsoleWriter.println("Invalid arguments.", Colors.RED);
            return;
        }

        List<Patient> patients = patientService.getAllPatients();

        //Test whether any patients are available:
        if (patients.isEmpty()) {
            ConsoleWriter.println("No patients available.", Colors.WHITE);
            return;
        }

        //Display headline:
        ConsoleWriter.print("Patient ID:", 36, Colors.DEFAULT);
        ConsoleWriter.print(" ", Colors.DEFAULT);
        ConsoleWriter.print("First name:", 24, Colors.DEFAULT);
        ConsoleWriter.print(" ", Colors.DEFAULT);
        ConsoleWriter.print("Last name:", 24, Colors.DEFAULT);
        ConsoleWriter.print(" ", Colors.DEFAULT);
        ConsoleWriter.println("Birthday:", Colors.DEFAULT);

        //Display each patient:
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
        for (Patient patient: patients) {
            ConsoleWriter.print(patient.getPatientId().toString(), 36, Colors.GREEN);
            ConsoleWriter.print(" ", Colors.DEFAULT);
            ConsoleWriter.print(patient.getPersonalData().getFirstname(), 24, Colors.DEFAULT);
            ConsoleWriter.print(" ", Colors.DEFAULT);
            ConsoleWriter.print(patient.getPersonalData().getLastname(), 24, Colors.DEFAULT);
            ConsoleWriter.print(" ", Colors.DEFAULT);
            ConsoleWriter.println(formatter.format(patient.getPersonalData().getBirthday()), Colors.DEFAULT);
        }
    }


    @Override
    public CommandInfo getInfo() {
        return new CommandInfo(
                "list patients",
                "Lists all available patients.",
                "list patients",
                Map.of()
        );
    }

}
