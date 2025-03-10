package de.christian2003.psychiatric.application.console;

import de.christian2003.psychiatric.adapters.console.Colors;
import de.christian2003.psychiatric.adapters.console.ConsoleWriter;
import de.christian2003.psychiatric.application.services.CrisisInterventionAreaService;
import de.christian2003.psychiatric.domain.rooms.CrisisInterventionArea;

import java.awt.*;
import java.io.Console;
import java.util.List;
import java.util.Map;

public class ListCiasCommand implements Command {

    private final CrisisInterventionAreaService crisisInterventionAreaService;


    public ListCiasCommand(CrisisInterventionAreaService crisisInterventionAreaService) throws NullPointerException {
        if (crisisInterventionAreaService == null) {
            throw new NullPointerException();
        }
        this.crisisInterventionAreaService = crisisInterventionAreaService;
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
        ConsoleWriter.println("Assigned Patient ID:", Colors.DEFAULT);

        //Display each crisis intervention area:
        for (CrisisInterventionArea crisisInterventionArea: crisisInterventionAreas) {
            ConsoleWriter.print(crisisInterventionArea.getRoomId().toString(), 36, Colors.GREEN);
            ConsoleWriter.print(" ", Colors.DEFAULT);
            ConsoleWriter.print(crisisInterventionArea.getRoomData().getDisplayName(), 24, Colors.DEFAULT);
            ConsoleWriter.print(" ", Colors.DEFAULT);
            if (crisisInterventionArea.hasAssignedPatient()) {
                ConsoleWriter.println(crisisInterventionArea.getAssignedPatient().toString(), 36, Colors.GREEN);
            }
            else {
                ConsoleWriter.println("None", Colors.WHITE);
            }
        }
    }

}
