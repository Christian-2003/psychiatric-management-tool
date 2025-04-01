package de.christian2003.psychiatric.application.console;

import de.christian2003.psychiatric.domain.people.Patient;
import de.christian2003.psychiatric.domain.services.PatientService;
import de.christian2003.psychiatric.plugins.console.Colors;
import de.christian2003.psychiatric.plugins.console.ConsoleWriter;
import de.christian2003.psychiatric.application.console.metadata.CommandInfo;
import de.christian2003.psychiatric.domain.services.CrisisInterventionAreaService;
import de.christian2003.psychiatric.domain.rooms.CrisisInterventionArea;

import java.util.List;
import java.util.Map;

public class ListCiasCommand implements Command {

    private final CrisisInterventionAreaService crisisInterventionAreaService;

    private final PatientService patientService;


    public ListCiasCommand(CrisisInterventionAreaService crisisInterventionAreaService, PatientService patientService) throws NullPointerException {
        if (crisisInterventionAreaService == null || patientService == null) {
            throw new NullPointerException();
        }
        this.crisisInterventionAreaService = crisisInterventionAreaService;
        this.patientService = patientService;
    }


    @Override
    public void execute(Map<String, String> args) {
        if (!args.isEmpty()) {
            ConsoleWriter.println("Invalid arguments.", Colors.RED);
            return;
        }

        List<CrisisInterventionArea> crisisInterventionAreas = crisisInterventionAreaService.getAllCrisisInterventionAreas();

        //Test whether any crisis intervention areas are available:
        if (crisisInterventionAreas.isEmpty()) {
            ConsoleWriter.println("No crisis intervention areas available.", Colors.WHITE);
            return;
        }

        //Display headline for table:
        ConsoleWriter.print("Crisis Intervention Area ID:", 36, Colors.DEFAULT);
        ConsoleWriter.print(" ", Colors.DEFAULT);
        ConsoleWriter.print("Name:", 24, Colors.DEFAULT);
        ConsoleWriter.print(" ", Colors.DEFAULT);
        ConsoleWriter.println("Assigned Patient:", Colors.DEFAULT);

        //Display each crisis intervention area:
        for (CrisisInterventionArea crisisInterventionArea: crisisInterventionAreas) {
            ConsoleWriter.print(crisisInterventionArea.getRoomId().toString(), 36, Colors.GREEN);
            ConsoleWriter.print(" ", Colors.DEFAULT);
            ConsoleWriter.print(crisisInterventionArea.getRoomData().getDisplayName(), 24, Colors.DEFAULT);
            ConsoleWriter.print(" ", Colors.DEFAULT);
            if (crisisInterventionArea.hasAssignedPatient()) {
                Patient patient = patientService.getPatientById(crisisInterventionArea.getAssignedPatient());
                if (patient != null) {
                    ConsoleWriter.print(patient.getPersonalData().getFirstname(), Colors.DEFAULT);
                    ConsoleWriter.print(" ", Colors.DEFAULT);
                    ConsoleWriter.print(patient.getPersonalData().getLastname(), Colors.DEFAULT);
                    ConsoleWriter.print(" (", Colors.DEFAULT);
                    ConsoleWriter.print(patient.getPatientId().toString(), Colors.GREEN);
                    ConsoleWriter.println(")", Colors.DEFAULT);
                }
                else {
                    ConsoleWriter.println(crisisInterventionArea.getAssignedPatient().toString(), Colors.GREEN);
                }
            }
            else {
                ConsoleWriter.println("None", Colors.WHITE);
            }
        }
    }


    @Override
    public CommandInfo getInfo() {
        return new CommandInfo(
                "list cias",
                "Lists all available crisis intervention areas.",
                "list cias",
                Map.of()
        );
    }

}
